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
class BasketViewModel(application: Application) : AndroidViewModel(application) {
    lateinit var repository: ProductRepository
    lateinit var allProducts: LiveData<List<Product>>

    // Инициализатор, создающий репозиторий и получающий все продукты
    init {
        viewModelScope.launch {
            try {
                val dao = ProductDatabase.getDatabase(application).productDao()
                repository = ProductRepository(dao)
                allProducts = repository.getAllProducts().asLiveData()
            } catch (e: Exception) {
                // Обработка ошибок при инициализации базы данных
                e.printStackTrace()
            }
        }
    }

    // Функция для вставки продукта в базу данных
    fun insertProduct(product: Product) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.insertProduct(product)
            } catch (e: Exception) {
                // Обработка ошибок при вставке продукта
                e.printStackTrace()
            }
        }
    }
}
