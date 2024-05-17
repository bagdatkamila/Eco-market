package com.example.ecomarket.room

import com.example.ecomarket.module.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ProductRepository(private val productDao: ProductDao) {
    suspend fun insertProduct(product: Product) {
        productDao.insertProduct(product)
    }

    suspend fun getAllProducts(): Flow<List<Product>> {
        return flow {
            emit(productDao.getAllProducts())
        }
    }
}