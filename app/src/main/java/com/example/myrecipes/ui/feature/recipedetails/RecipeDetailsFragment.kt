package com.example.myrecipes.ui.feature.recipedetails

import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.myrecipes.R
import com.example.myrecipes.data.model.entity.RecipesEntity
import com.example.myrecipes.databinding.FragmentRecipeDetailsBinding
import com.example.myrecipes.ui.common.BaseFragment
import com.example.myrecipes.ui.extension.showDialog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*


class RecipeDetailsFragment : BaseFragment(R.layout.fragment_recipe_details) {
    override val viewModel: RecipeDetailsViewModel by viewModel()
    private var binding: FragmentRecipeDetailsBinding? = null
    private val args: RecipeDetailsFragmentArgs by navArgs()
    private var recipeImgUri: Uri? = null
    private var recipeId: String? = null

    private val getContent =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            uri?.let { viewModel.emitImageUriFromInternalStorage(it) }
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (binding == null) {
            binding = FragmentRecipeDetailsBinding.inflate(inflater, container, false)
        }
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recipeId = args.recipeId
        viewModel.getRecipeById(recipeId!!)
        observeForCurrentRecipe()
        observeForExternalStorageImageUri()
        setListeners()
    }

    private fun observeForCurrentRecipe() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.recipeSharedFlow
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collectLatest { recipeEntity ->
                    binding?.apply {
                        if (recipeImgUri == null) {
                            recipeImgUri = recipeEntity.imageUri
                            etDescription.setText(recipeEntity.description)
                            etTitle.setText(recipeEntity.title)
                            recipeImgUri?.let {
                                if (it.toString().contains("http")) {
                                    Glide.with(requireContext()).load(recipeImgUri).into(imgRecipe)
                                } else {
                                    imgRecipe.setImageURI(recipeImgUri)
                                }
                            }
                        }
                    }
                }
        }
    }

    private fun observeForExternalStorageImageUri() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.externalStorageUriSharedFlow
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collectLatest { uri ->
                    binding?.apply {
                        uri?.let {
                            recipeImgUri = it
                            imgRecipe.setImageURI(it)
                        }
                    }
                }
        }
    }

    private fun setListeners() {
        binding?.apply {
            imgRecipe.setOnClickListener {
                if (isPermissionsGranted()) {
                    getContent.launch("image/*")
                } else {
                    showDialog()
                }
            }
            btnSave.setOnClickListener {

                val recipe = RecipesEntity(
                    etTitle.text.toString(),
                    etDescription.text.toString(),
                    recipeImgUri ?: Uri.parse("android.resource://your.package.here/drawable/ic_launcher_foreground") ,
                    UUID.randomUUID().toString()
                )
                viewModel.updateRecipe(recipe)
                findNavController().popBackStack()
            }
        }
    }


    private fun isPermissionsGranted(): Boolean {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            ) ==
            PackageManager.PERMISSION_GRANTED
        ) {
            return true
        }
        return false
    }

}