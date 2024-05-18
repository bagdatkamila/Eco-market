package com.example.ecomarket.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.ecomarket.databinding.ItemProductBinding
import com.example.ecomarket.module.CategoryModel

class HomeAdapter(private val onClickListener:(Int)->Unit) : ListAdapter<CategoryModel, HomeAdapter.CategoryViewHolder>(CategoryDiffCallback()) {
    private lateinit var listener:OnItemClickListener
    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener : OnItemClickListener){
        this.listener = listener
    }

    var onClick: (String) -> Unit = {}

    class CategoryViewHolder(private val cardCategoryBinding: ItemProductBinding) :
        RecyclerView.ViewHolder(cardCategoryBinding.root) {
        fun bind(category: CategoryModel, onClick: (String) -> Unit = {}) {
            cardCategoryBinding.ivProduct.load(category.image)
            cardCategoryBinding.tvName.text = category.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val categoryLayout =
            ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(categoryLayout)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(getItem(position), onClick)
        holder.itemView.setOnClickListener {
            onClickListener.invoke(position)
        }
    }

    class CategoryDiffCallback : DiffUtil.ItemCallback<CategoryModel>() {
        override fun areItemsTheSame(oldItem: CategoryModel, newItem: CategoryModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: CategoryModel, newItem: CategoryModel): Boolean {
            return oldItem == newItem
        }
    }
}