package com.example.myrecipes.ui.feature.recipes

import android.os.Bundle
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
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
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
        return when (item.getItemId()) {
            R.id.deleteId -> {
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean = if (menu?.findItem(R.id.deleteId)?.isVisible == true) {
        recipesAdapter?.resetSelectedItems()
        true
        } else {
            findNavController().navigateUp()
        }

    private fun setViews(view: View) {
        fac = view.findViewById(R.id.btnFAB)
    }

    private fun initRv() {
        recipesAdapter = RecipesAdapter(
            onItemClicked = {id ->
                navigateToDetailScreen(id)
            }, onChangeMenuItemVisibility = {
                menu?.findItem(R.id.deleteId)?.isVisible = it
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
    }

    private fun setListeners() {
        fac.setOnClickListener {
            val action = RecipesFragmentDirections.actionRecipesFragmentToAddRecipeFragment()
            findNavController().navigate(action)
        }
    }
}