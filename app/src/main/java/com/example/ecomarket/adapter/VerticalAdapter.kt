package com.example.ecomarket.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.ecomarket.databinding.ItemDetailVerticalBinding
import com.example.ecomarket.module.DetailModel
import com.example.ecomarket.module.toProduct
import com.example.ecomarket.room.BasketViewModel
import java.text.NumberFormat

class VerticalAdapter(private val onClick : (Int) -> Unit,
                      private val basketViewModel: BasketViewModel
): RecyclerView.Adapter<VerticalAdapter.CategoryViewHolder>() {


    private val categoryList = arrayListOf<DetailModel>()

    var addProduct = false

    fun setData(itemList: List<DetailModel>) {
        categoryList.clear()
        categoryList.addAll(itemList)
        notifyItemChanged(itemList.size)
    }

    class CategoryViewHolder(private val cardCategoryBinding: ItemDetailVerticalBinding) :
        RecyclerView.ViewHolder(cardCategoryBinding.root) {
        val image = cardCategoryBinding.ivProduct
        val tv_name = cardCategoryBinding.tvName
        val btn_add = cardCategoryBinding.btnAdd
        val rel_btn = cardCategoryBinding.relCount
        fun bind(category: DetailModel) {
            cardCategoryBinding.tvName.text = category.title
            cardCategoryBinding.tvPrice.text = NumberFormat.getCurrencyInstance().format(category.price) + " c"
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val categoryLayout =
            ItemDetailVerticalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(categoryLayout)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {

        holder.bind(categoryList[position])
        holder.image.load(categoryList[position].image)

        val product = categoryList[position].toProduct()

        holder.itemView.setOnClickListener {
            onClick.invoke(position)
        }

        holder.btn_add.setOnClickListener {
            basketViewModel.insertProduct(product)
            if (addProduct) {
                holder.rel_btn.visibility = View.GONE
                holder.btn_add.visibility = View.VISIBLE
                addProduct = true

            } else {
                holder.rel_btn.visibility = View.VISIBLE
                holder.btn_add.visibility = View.GONE
                addProduct = false
            }
        }
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

}