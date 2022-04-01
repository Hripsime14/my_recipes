package com.example.myrecipes.ui.feature.recipes.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myrecipes.data.local.db.dao.RecipeDao
import com.example.myrecipes.data.model.data.RecipeViewData

import com.example.myrecipes.databinding.ItemRecipeBinding

class RecipesAdapter(private val onItemClicked: (Int) -> Unit,
                    private val onChangeMenuItemVisibility: (Boolean) -> Unit)
    :ListAdapter<RecipeViewData, RecipesAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemRecipeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentRecipe = currentList[position]
        holder.bind(currentRecipe)
    }

    fun resetSelectedItems() {
        currentList.onEach {
            it.isSelected = false
        }
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val itemRecipeBinding: ItemRecipeBinding):
        RecyclerView.ViewHolder(itemRecipeBinding.root) {

        init {
            itemView.setOnClickListener {
                if (currentList.any { it.isSelected }) {
                    changeItemSelectionByPosition(currentList[adapterPosition])
                } else {
                    onItemClicked.invoke(currentList[adapterPosition].id)
                }
                onChangeMenuItemVisibility.invoke(currentList.any { it.isSelected })
            }
            itemView.setOnLongClickListener {
                val currentRecipe = currentList[adapterPosition]
                changeItemSelectionByPosition(currentRecipe)
                onChangeMenuItemVisibility.invoke(currentList.any { it.isSelected })
                true
            }
        }

        private fun changeItemSelectionByPosition(currentRecipe: RecipeViewData) {
            currentRecipe.isSelected = !currentRecipe.isSelected
            notifyItemChanged(adapterPosition)
        }

        fun bind(recipeViewData: RecipeViewData) = with(itemRecipeBinding) {
            itemView.apply {
                tvRecipeTitle.text = recipeViewData.title
                tvRecipeDescription.text = recipeViewData.description
                imgRecipeIcon.setImageURI(recipeViewData.imageUri)
                vwBackground.isVisible = recipeViewData.isSelected
            }
        }
    }

    class DiffCallback: DiffUtil.ItemCallback<RecipeViewData>() {
        override fun areItemsTheSame(oldItem: RecipeViewData, newItem: RecipeViewData): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: RecipeViewData, newItem: RecipeViewData): Boolean =
            oldItem.hashCode() == newItem.hashCode()

    }
}