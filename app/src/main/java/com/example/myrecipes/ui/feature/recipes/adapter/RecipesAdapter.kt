package com.example.myrecipes.ui.feature.recipes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.example.myrecipes.data.model.entity.RecipesEntity
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

import com.example.myrecipes.databinding.ItemRecipeBinding

class RecipesAdapter()
    :ListAdapter<RecipesEntity, RecipesAdapter.ViewHolder>(DiffCallback()) {

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

    inner class ViewHolder(private val itemRecipeBinding: ItemRecipeBinding):
        RecyclerView.ViewHolder(itemRecipeBinding.root) {

        fun bind(recipesEntity: RecipesEntity) = with(itemRecipeBinding) {
            itemView.apply {
                tvRecipeTitle.text = recipesEntity.title
                tvRecipeDescription.text = recipesEntity.description
            }
        }
    }

    class DiffCallback: DiffUtil.ItemCallback<RecipesEntity>() {
        override fun areItemsTheSame(oldItem: RecipesEntity, newItem: RecipesEntity): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: RecipesEntity, newItem: RecipesEntity): Boolean =
            oldItem.hashCode() == newItem.hashCode()

    }
}