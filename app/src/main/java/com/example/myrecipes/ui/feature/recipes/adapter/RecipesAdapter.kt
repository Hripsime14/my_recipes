package com.example.myrecipes.ui.feature.recipes.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myrecipes.data.model.data.LoadedRecipesData
import com.example.myrecipes.data.model.data.RecipeViewData

import com.example.myrecipes.databinding.ItemRecipeBinding
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*

class RecipesAdapter(private val onItemClicked: (String) -> Unit,
                    private val onChangeMenuItemVisibility: (Boolean) -> Unit)
    :ListAdapter<RecipeViewData, RecipesAdapter.ViewHolder>(DiffCallback()) {

    private val eventChannel = Channel<Int> {}
    val eventFlow = eventChannel.receiveAsFlow()
    private val visibilityChangeEventChannel = Channel<Boolean> {}
    val visibilityChangeFlow = visibilityChangeEventChannel.receiveAsFlow()

    private val _itemClickSharedFlow = MutableSharedFlow<Int>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val itemClickSharedFlow = _itemClickSharedFlow.asSharedFlow()


    private val _visibilityChangeSharedFlow = MutableSharedFlow<Boolean>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val visibilityChangeSharedFlow = _visibilityChangeSharedFlow.asSharedFlow()

    private var coroutineScope: CoroutineScope? = null

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

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        coroutineScope = CoroutineScope(Dispatchers.IO)
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        coroutineScope?.cancel()
        coroutineScope = null
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
                    //version 1 (lambda)
                    onItemClicked.invoke(currentList[adapterPosition].id)

//                    //version 2 (channel)
//                    coroutineScope?.launch {
//                        eventChannel.send(currentList[adapterPosition].id)
////                        eventChannel.close()
//                    }
//
//                    //version 3 (flow)
//                    coroutineScope?.launch {
//                        _itemClickSharedFlow.emit(currentList[adapterPosition].id)
//                    }

//                    //version 4 (flow without scope)
//                    _itemClickSharedFlow.tryEmit(currentList[adapterPosition].id)

                }
                onChangeMenuItemVisibility.invoke(currentList.any { it.isSelected })
            }
            itemView.setOnLongClickListener {
                val currentRecipe = currentList[adapterPosition]
                changeItemSelectionByPosition(currentRecipe)

                //version 1 (lambda)
                onChangeMenuItemVisibility.invoke(currentList.any { it.isSelected })

//
//                //version 2 (channel)
//                coroutineScope?.launch {
//                    visibilityChangeEventChannel.send(currentList.any { it.isSelected })
////                    visibilityChangeEventChannel.close()
//                }
//
                //version 3 (flow)
//                coroutineScope?.launch {
//                    Log.d("testest", ": emited")
//                    _visibilityChangeSharedFlow.emit(currentList.any { it.isSelected })
//                }

//
//                //version 4 (flow without scope)
//                _visibilityChangeSharedFlow.tryEmit(currentList.any {
//                    Log.d("testest", ": emited")
//                    it.isSelected
//                })

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
                recipeViewData.imageUri.let {
                    if (it.toString().contains("http")) {
                        Glide.with(itemView.context).load(it).into(imgRecipeIcon)
                    } else {
                        imgRecipeIcon.setImageURI(it)
                    }
                }
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