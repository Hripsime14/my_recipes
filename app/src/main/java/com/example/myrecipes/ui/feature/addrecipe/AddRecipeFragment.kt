package com.example.myrecipes.ui.feature.addrecipe

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.myrecipes.R
import com.example.myrecipes.data.model.entity.RecipesEntity
import com.example.myrecipes.databinding.FragmentAddRecipeBinding
import com.example.myrecipes.ui.common.BaseFragment
import com.example.myrecipes.ui.extension.showDialog
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

const val MIMETYPE_IMAGE = "image/*"

class AddRecipeFragment : BaseFragment(R.layout.fragment_add_recipe) {
    private var binding: FragmentAddRecipeBinding? = null
    override val viewModel: AddRecipeViewModel by viewModel()
    private var imageRecipeUri: Uri? = null

    private val requestPermissions = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            val deniedPermissions = arrayListOf<String>()
            permissions.entries.forEach {
                if (!it.value) {
                    deniedPermissions.add(it.key)
                }
                deniedPermissions.size.takeIf { size -> size > 0 }?.let {
                    showDialog()
                }
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (binding == null) {
            binding = FragmentAddRecipeBinding.inflate(inflater, container, false)
        }
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        observeForExternalStorageImageUri()
        isPermissionsGranted().takeIf { !it }?.let {
            requestPermission()
        }
    }

    private fun observeForExternalStorageImageUri() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.externalStorageUriSharedFlow
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collectLatest { uri ->
                    binding?.apply {
                        imageRecipeUri = uri
                        imgRecipe.setImageURI(uri)
                    }
                }
        }
    }

    //create custom contract for getting external content uri
    private val resultContract = object : ActivityResultContract<Any?, Uri?>() {
        override fun createIntent(context: Context, input: Any?): Intent =
            Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI).apply {
                type = MIMETYPE_IMAGE
            }

        override fun parseResult(resultCode: Int, intent: Intent?): Uri? {
            val isSuccess = resultCode == Activity.RESULT_OK
            if (isSuccess) {
                return intent?.data
            }
            return null
        }
    }

    private val getContract: ActivityResultLauncher<Any?> =
        registerForActivityResult(resultContract) { uri ->
            uri.takeIf { it != null && activity != null }?.let {
                viewModel.emitImageUriFromInternalStorage(it)
            }
        }

    private fun setListeners() {
        binding?.apply {
            btnSave.setOnClickListener {
                val title = etTitle.text.toString()
                val description = etDescription.text.toString()

                val defUri = Uri.parse("android.resource://com.example.myrecipes/drawable-v24/ic_launcher_foreground.xml")

                val currentRecipe = RecipesEntity(title, description, imageRecipeUri?: defUri)
                saveRecipe(currentRecipe)
            }

            imgRecipe.setOnClickListener {
                if (isPermissionsGranted()) {
                    getContract.launch(null)
                } else {
                    requestPermission()
                }
            }
        }
    }

    private fun requestPermission() =
        requestPermissions.launch(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE))

    private fun isPermissionsGranted() =
        ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.READ_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED


    private fun saveRecipe(currentRecipe: RecipesEntity) {
        currentRecipe.let {
            viewModel.addRecipe(it)
            findNavController().popBackStack()
        }
    }
}