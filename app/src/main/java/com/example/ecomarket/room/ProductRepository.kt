package com.example.ecomarket.room

import com.example.ecomarket.module.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


// Репозиторий для управления продуктами через DAO
class ProductRepository(private val productDao: ProductDao) {
    suspend fun insertProduct(product: Product) {
        productDao.insertProduct(product)    // Метод для вставки продукта
    }

    // Метод для получения всех продуктов в виде потока данных
    suspend fun getAllProducts(): Flow<List<Product>> {
        return flow {
            emit(productDao.getAllProducts())
        }
    }
}