package com.example.ecomarket.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import coil.load
import com.example.ecomarket.databinding.ItemSavedBinding
import com.example.ecomarket.module.Product


class BasketAdapter(private val context: Context) : RecyclerView.Adapter<BasketAdapter.BasketViewHolder>() {

    private var productList = emptyList<Product>()

    class BasketViewHolder(val binding: ItemSavedBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketViewHolder {
        val binding = ItemSavedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BasketViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BasketViewHolder, position: Int) {
        val product = productList[position]
        holder.binding.tvName.text = product.name
        holder.binding.tvPrice.text = product.price.toString()
        holder.binding.tvCount.text = product.count.toString()
        holder.binding.tvDesc.text = "Цена за штуку: ${product.price}"
        holder.binding.ivProduct.load(product.image)

        holder.binding.btnMinus.setOnClickListener {
            if (product.count > 0) {
                product.count--
                updateProduct(product)
            }
        }

        holder.binding.btnPlus.setOnClickListener {
            product.count++
            updateProduct(product)
        }
    }

    override fun getItemCount() = productList.size

    fun setData(products: List<Product>) {
        this.productList = products
        notifyDataSetChanged()
    }

    private fun updateProduct(product: Product) {
//   тут должно было быть обновление данных в базе данных, но мы не поняли как это в адаптере сделать
    }
}