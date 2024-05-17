package com.example.ecomarket.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import coil.load
import com.example.ecomarket.databinding.ItemSavedBinding
import com.example.ecomarket.module.Product
import com.example.ecomarket.room.BasketViewModel


class BasketAdapter(private val context: Context, private val basketViewModel: BasketViewModel) : RecyclerView.Adapter<BasketAdapter.BasketViewHolder>() {

    private var productList = emptyList<Product>()

    class BasketViewHolder(val binding: ItemSavedBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketViewHolder {
        val binding = ItemSavedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BasketViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BasketViewHolder, position: Int) {
        val product = productList[position]
        holder.binding.tvName.text = product.name
        holder.binding.tvPrice.text = (product.price * product.count).toString()
        holder.binding.tvCount.text = product.count.toString()
        holder.binding.tvDesc.text = "Цена за штуку: ${product.price}"
        holder.binding.ivProduct.load(product.image)

        holder.binding.btnMinus.setOnClickListener {
            if (product.count > 1) {
                product.count--
            }
            else{
                basketViewModel.deleteProduct(product)// Удаляем продукт из базы данных
            }
            notifyDataSetChanged()
        }

        holder.binding.btnPlus.setOnClickListener {
            product.count++
            basketViewModel.updateProduct(product)
            notifyDataSetChanged()
        }
    }

    override fun getItemCount() = productList.size

    fun setData(products: List<Product>) {
        this.productList = products
        notifyDataSetChanged()
    }

    fun updateData(){
        notifyDataSetChanged()
    }

//dif util не смог...
}