package com.example.myrecipes.ui.feature.addrecipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.myrecipes.R
import com.example.myrecipes.data.model.entity.RecipesEntity
import com.example.myrecipes.databinding.FragmentAddRecipeBinding
import com.example.myrecipes.ui.common.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel


class AddRecipeFragment : BaseFragment(R.layout.fragment_add_recipe) {
    private var binding: FragmentAddRecipeBinding? = null
    override val viewModel: AddRecipeViewModel by viewModel()

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
    }

    private fun setListeners() {
        binding?.apply {
            btnSave.setOnClickListener {
                val title = etTitle.text.toString()
                val description = etDescription.text.toString()
//                val uri = Uri.parse(imgRecipe.drawable.toString())

                val currentRecipe = RecipesEntity(title, description)
                saveRecipe(currentRecipe)
            }
        }
    }

    private fun saveRecipe(currentRecipe: RecipesEntity) {
        currentRecipe.let {
            viewModel.addRecipe(it)
            findNavController().popBackStack()
        }
    }
}