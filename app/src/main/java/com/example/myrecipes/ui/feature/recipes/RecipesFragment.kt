package com.example.myrecipes.ui.feature.recipes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myrecipes.R
import com.example.myrecipes.data.model.entity.RecipesEntity
import com.example.myrecipes.databinding.FragmentRecipesBinding
import com.example.myrecipes.ui.common.BaseFragment
import com.example.myrecipes.ui.feature.recipes.adapter.RecipesAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class RecipesFragment : BaseFragment(R.layout.fragment_recipes) {
    private var binding: FragmentRecipesBinding? = null
    private lateinit var fac: FloatingActionButton
    private var recipesAdapter: RecipesAdapter? = null
    override val viewModel: RecipesViewModel by viewModel()
    private var list: List<RecipesEntity>? = null

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
        setViews(view)
        setListeners()
        initRv()
        observeData()
    }

    private fun setViews(view: View) {
        fac = view.findViewById(R.id.btnFAB)
    }


    private fun initRv() {
        recipesAdapter = RecipesAdapter(
            onItemClicked = {
                val action = RecipesFragmentDirections.actionRecipesFragmentToRecipeDetailsFragment(it)
                findNavController().navigate(action)
            }, onItemLongClicked = {

            }
            )
        binding?.rvRecipes?.apply {
            adapter = recipesAdapter
            layoutManager = LinearLayoutManager(requireContext())
            hasFixedSize()
        }
    }

    private fun observeData() {
        viewModel.getAllRecipes()
            .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach {
                list = it
                recipesAdapter?.submitList(list)
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun setListeners() {
        fac.setOnClickListener {
            val action = RecipesFragmentDirections.actionRecipesFragmentToAddRecipeFragment()
            findNavController().navigate(action)
        }
    }
}