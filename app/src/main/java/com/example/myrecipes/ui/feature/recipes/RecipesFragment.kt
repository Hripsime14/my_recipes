package com.example.myrecipes.ui.feature.recipes

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myrecipes.R
import com.example.myrecipes.data.model.data.RecipeViewData
import com.example.myrecipes.data.model.entity.RecipesEntity
import com.example.myrecipes.databinding.FragmentRecipesBinding
import com.example.myrecipes.ui.common.BaseFragment
import com.example.myrecipes.ui.feature.recipes.adapter.RecipesAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class RecipesFragment : BaseFragment(R.layout.fragment_recipes) {
    private var binding: FragmentRecipesBinding? = null
    private lateinit var fac: FloatingActionButton
    private var recipesAdapter: RecipesAdapter? = null
    override val viewModel: RecipesViewModel by viewModel()
    private var list: List<RecipeViewData>? = null
    private var menu: Menu? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (binding == null) {
            binding = FragmentRecipesBinding.inflate(inflater, container, false)
        }
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        setViews(view)
        setListeners()
        initRv()
        observeData()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.options_menu, menu)
        this.menu = menu
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.deleteId -> {
                recipesAdapter?.let {adapter ->
                    val selectedItemList = adapter.currentList.filter { it.isSelected }
                    if (selectedItemList.size == 1) {
                        viewModel.deleteRecipeById(selectedItemList[0].id)
                    } else {
                        viewModel.deleteRecipesArray(selectedItemList)
                    }
                }
                menu?.findItem(R.id.deleteId)?.isVisible = false
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setViews(view: View) {
        fac = view.findViewById(R.id.btnFAB)
    }

    private fun initRv() {
        recipesAdapter = RecipesAdapter(
            //version 1 (lambda)
            onItemClicked = {id ->
                itemClickAction(id)
            }, onChangeMenuItemVisibility = {
                visibilityChangeAction(it)
            }
        )

        binding?.rvRecipes?.apply {
            adapter = recipesAdapter
            layoutManager = LinearLayoutManager(requireContext())
            hasFixedSize()
        }
    }

    private fun navigateToDetailScreen(id: Int) {
        val action = RecipesFragmentDirections.actionRecipesFragmentToRecipeDetailsFragment(id)
        findNavController().navigate(action)
    }

    private fun observeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getAllRecipes().onEach {
                list = it
                recipesAdapter?.submitList(list)
            }.launchIn(viewLifecycleOwner.lifecycleScope)
        }

//        //version 2 (channel)
//
//        viewLifecycleOwner.lifecycleScope.launch {
//            recipesAdapter?.eventFlow?.collect {
//                itemClickAction(it)
//            }
//        }
//
//        viewLifecycleOwner.lifecycleScope.launch {
//            recipesAdapter?.visibilityChangeFlow?.collect {
//                visibilityChangeAction(it)
//            }
//        }
//
        //version 3 (flow), version 4 (flow without scope)

//        viewLifecycleOwner.lifecycleScope.launch{
//            recipesAdapter?.itemClickSharedFlow?.collect {
//                itemClickAction(it)
//            }
//        }

//        viewLifecycleOwner.lifecycleScope.launch {
//            recipesAdapter?.visibilityChangeSharedFlow?.collect {
//                Log.d("testest", ": called, it = " + it)
//
//                visibilityChangeAction(it)
//            }
//        }
        viewLifecycleOwner.lifecycleScope.launch {
            recipesAdapter?.visibilityChangeSharedFlow?.onEach {
                Log.d("testest", ": called, it = " + it)
                visibilityChangeAction(it)
            }?.launchIn(viewLifecycleOwner.lifecycleScope)
        }
    }

    private fun itemClickAction(id: Int) {
        navigateToDetailScreen(id)
    }

    private fun visibilityChangeAction(isAnyItemSelected: Boolean) {
        menu?.findItem(R.id.deleteId)?.isVisible = isAnyItemSelected
    }

    private fun setListeners() {
        fac.setOnClickListener {
            val action = RecipesFragmentDirections.actionRecipesFragmentToAddRecipeFragment()
            findNavController().navigate(action)
        }
    }
}