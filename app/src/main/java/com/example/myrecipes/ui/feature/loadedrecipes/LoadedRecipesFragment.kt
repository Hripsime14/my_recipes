package com.example.myrecipes.ui.feature.loadedrecipes

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ProgressBar
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myrecipes.R
import com.example.myrecipes.data.repository.util.Resource
import com.example.myrecipes.databinding.FragmentLoadedRecipesBinding
import com.example.myrecipes.ui.common.BaseFragment
import com.example.myrecipes.ui.feature.loadedrecipes.adapter.LoadedRecipesAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoadedRecipesFragment : BaseFragment(R.layout.fragment_loaded_recipes) {
    override val viewModel: LoadedRecipesViewModel by viewModel()
    private var recipesAdapter: LoadedRecipesAdapter? = null
    private var binding: FragmentLoadedRecipesBinding? = null
    private var menu: Menu? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (binding == null) {
            binding = FragmentLoadedRecipesBinding.inflate(inflater, container, false)
        }
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        setViews()
        initRv()

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.recipeSharedFlow.collectLatest { result ->
                when (result) {
                    is Resource.Loading -> {
                        showLoading()
                    }
                    is Resource.Success -> {
                        hideLoading()
                        recipesAdapter?.submitList(result.data)
                    }
                    is Resource.Error -> {
                        hideLoading()
                    }
                }
            }
        }

        viewModel.loadRecipes()

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.options_menu, menu)
        this.menu = menu
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.addId -> {
                addSelectedItemsToRecipeList()
//                findNavController().popBackStack()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun visibilityChangeAction(isAnyItemSelected: Boolean) {
        menu?.findItem(R.id.addId)?.isVisible = isAnyItemSelected
    }

    private fun showLoading() {
        binding?.loading?.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        binding?.loading?.visibility = View.INVISIBLE
    }

    private fun setViews() {

    }

    private fun initRv() {
        recipesAdapter = LoadedRecipesAdapter {changeMenuItemVisibility ->
            visibilityChangeAction(changeMenuItemVisibility)
        }
        binding?.rvRecipes?.apply {
            this.adapter = recipesAdapter
            layoutManager = LinearLayoutManager(requireContext())
            hasFixedSize()
        }
    }

    private fun addSelectedItemsToRecipeList() {
        val selectedItemList = recipesAdapter?.currentList?.filter { it.isSelected }
        if (selectedItemList != null) {
            viewModel.addRecipes(selectedItemList)
        }
    }
}