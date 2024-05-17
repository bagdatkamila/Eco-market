package com.example.ecomarket.room

import com.example.ecomarket.module.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.catch

// Репозиторий для управления продуктами через DAO
class ProductRepository(private val productDao: ProductDao) {
    // Метод для вставки продукта
    suspend fun insertProduct(product: Product) {
        try {
            productDao.insertProduct(product)
        } catch (e: Exception) {
            // Обработка ошибок при вставке продукта
            e.printStackTrace()
        }
    }

    // Метод для получения всех продуктов в виде потока данных
    suspend fun getAllProducts(): Flow<List<Product>> {
        return flow {
            emit(productDao.getAllProducts())
        }.catch { e ->
            // Обработка ошибок при получении продуктов
            e.printStackTrace()
        }
    }
}
