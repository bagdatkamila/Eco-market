package com.example.neobischallengeandroidapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.neobischallengeandroidapp.R
import com.example.neobischallengeandroidapp.databinding.ItemCategoriesBinding
import com.example.neobischallengeandroidapp.module.CategoryModel

class Horizontaladapter(val context: Context, private val onItemSelected: (Int) -> Unit) :
    RecyclerView.Adapter<Horizontaladapter.CategoryViewHolder>() {

    private var selectedPosition = RecyclerView.NO_POSITION


    private val categoryList = arrayListOf<CategoryModel>()
    fun setData(itemList: List<CategoryModel>, id: Int) {
        categoryList.clear()
        categoryList.addAll(itemList)
        notifyItemChanged(itemList.size)
        this.selectedPosition = id
    }

    inner class CategoryViewHolder(private val cardCategoryBinding: ItemCategoriesBinding) :
        RecyclerView.ViewHolder(cardCategoryBinding.root) {
        val cardView = cardCategoryBinding.cvCategory
        val tv_name = cardCategoryBinding.tvName

        init {
            itemView.setOnClickListener {
                onItemSelected(adapterPosition)
            }
        }

        fun bind(category: CategoryModel) {
            cardCategoryBinding.tvName.text = category.name
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val categoryLayout =
            ItemCategoriesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(categoryLayout)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {

        holder.bind(categoryList[position])

        val backgroundColor =
            if (position == selectedPosition) holder.itemView.context.getColor(R.color.green_selected_color) else android.graphics.Color.WHITE
        holder.cardView.setCardBackgroundColor(backgroundColor)

        if (position == selectedPosition) {
            holder.cardView.strokeWidth = 1
            holder.cardView.strokeColor =
                holder.itemView.context.getColor(R.color.green_selected_color)
            holder.tv_name.setTextColor(holder.itemView.context.getColor(R.color.white))
        } else {
            holder.cardView.strokeWidth = 2
            holder.tv_name.setTextColor(holder.itemView.context.getColor(R.color.grey))
            holder.cardView.strokeColor = holder.itemView.context.getColor(R.color.grey)
        }

    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    fun updateSelectedItem(newPosition: Int) {
        selectedPosition = newPosition
        notifyDataSetChanged()
    }

}