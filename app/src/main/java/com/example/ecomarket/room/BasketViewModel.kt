package com.example.ecomarket.room

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.ecomarket.module.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import androidx.lifecycle.asLiveData


class BasketViewModel (application: Application) : AndroidViewModel(application) {
    lateinit var repository: ProductRepository
    lateinit var allProducts: LiveData<List<Product>>

    init {
        viewModelScope.launch {
            val dao = ProductDatabase.getDatabase(application).productDao()
            repository = ProductRepository(dao)
            allProducts = repository.getAllProducts().asLiveData()
        }
    }

    // Function to insert a product into the database
    fun insertProduct(product: Product) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertProduct(product)
        }
    }
}