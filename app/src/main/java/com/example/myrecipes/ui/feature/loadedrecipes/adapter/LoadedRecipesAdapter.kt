package com.example.myrecipes.ui.feature.loadedrecipes.adapter

import android.os.Build
import android.text.Html
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myrecipes.R
import com.example.myrecipes.data.model.data.RecipeViewData
import com.example.myrecipes.databinding.ItemRecipe2Binding


class LoadedRecipesAdapter(private val changeMenuItemVisibility: (Boolean) -> Unit)
    : ListAdapter<RecipeViewData, LoadedRecipesAdapter.ViewHolder>(DiffCallback()) {
    private var selectedNumbers: Int = 0
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LoadedRecipesAdapter.ViewHolder = ViewHolder(
        ItemRecipe2Binding.inflate(
        LayoutInflater.from(parent.context),
        parent,
        false
        )
    )

    override fun onBindViewHolder(holder: LoadedRecipesAdapter.ViewHolder, position: Int) {
        val currentRecipe = currentList[position]
        holder.bind(currentRecipe)
    }

    inner class ViewHolder(private val itemRecipeBinding: ItemRecipe2Binding):
        RecyclerView.ViewHolder(itemRecipeBinding.root) {

        init {
            itemView.setOnClickListener {
                changeBackgroundColor(false, currentList[adapterPosition])
                changeMenuItemVisibility.invoke(selectedNumbers > 0)
//                Log.d("sssssssh", ": adapterPosition = $adapterPosition when click")
//                if (currentList.any { it.isSelected }) {
//                    val currentRecipe = currentList[adapterPosition]
//                    changeItemSelection(currentRecipe)
//                }
            }
            itemView.setOnLongClickListener {
                changeBackgroundColor(true, currentList[adapterPosition])
                changeMenuItemVisibility.invoke(selectedNumbers > 0)
//                Log.d("sssssssh", ": adapterPosition = $adapterPosition when long click")
//                val currentRecipe = currentList[adapterPosition]
//                changeItemSelection(currentRecipe)
                true
            }
        }

        private fun changeBackgroundColor(isLongClicked: Boolean, item: RecipeViewData) {
            val currentBackground = itemView.findViewById<View>(R.id.vwBackground2)
            Log.d("gggggggg", "changeBackgroundColor: visibility is ${currentBackground.visibility == View.VISIBLE}")
            if (currentBackground.visibility == View.VISIBLE) {
//                currentBackground.visibility = View.INVISIBLE
//                currentList[adapterPosition].isSelected = false
                item.isSelected = false
                selectedNumbers--
            } else {
                if (isLongClicked || (!isLongClicked && selectedNumbers > 0)) {
//                    currentBackground.visibility = View.VISIBLE
//                    currentList[adapterPosition].isSelected = true
                    item.isSelected = true
                    selectedNumbers++
                }
            }
            notifyItemChanged(adapterPosition)
        }

        private fun changeItemSelection(currentRecipe: RecipeViewData) {
            currentRecipe.isSelected = !currentRecipe.isSelected
            Log.d("ssssssshh", ": adapterPosition = $adapterPosition")

            notifyItemChanged(adapterPosition)
        }

        fun bind(recipeViewData: RecipeViewData) = with(itemRecipeBinding){
            itemView.apply {
                tvRecipeTitle.text = recipeViewData.title
                tvRecipeDescription.text = showLinksInString(recipeViewData.description)
                makeLinksClickable(tvRecipeDescription)
                Log.d("gggggggg", "bind: $selectedNumbers , isk ${recipeViewData.isSelected}")
                vwBackground2.isVisible = recipeViewData.isSelected
                loadImage(recipeViewData, imgRecipeIcon)
            }
        }

        private fun loadImage(recipeViewData: RecipeViewData, imgRecipeIcon: ImageView) {
            Glide.with(itemView.context).load(recipeViewData.imageUri).into(imgRecipeIcon)
        }

        private fun makeLinksClickable(tvRecipeDescription: TextView) {
            tvRecipeDescription.movementMethod = LinkMovementMethod.getInstance()
        }

        private fun showLinksInString(text: String): Spanned? {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Html.fromHtml(text, Html.FROM_HTML_MODE_COMPACT)
            } else {
                Html.fromHtml(text)
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