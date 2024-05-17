package com.example.ecomarket.room

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.ecomarket.module.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import androidx.lifecycle.asLiveData


// ViewModel для управления данными корзины продуктов
class BasketViewModel (application: Application) : AndroidViewModel(application) {
    lateinit var repository: ProductRepository
    lateinit var allProducts: LiveData<List<Product>>

    // Инициализатор, создающий репозиторий и получающий все продукты
    init {
        viewModelScope.launch {
            val dao = ProductDatabase.getDatabase(application).productDao()
            repository = ProductRepository(dao)
            allProducts = repository.getAllProducts().asLiveData()
        }
    }

    // Функция для вставки продукта в базу данных
    fun insertProduct(product: Product) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertProduct(product)
        }
    }
}