package com.example.ecomarket.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.ecomarket.module.Product

// DAO для управления продуктами в базе данных
@Dao
interface ProductDao {
    // Метод для вставки продукта
    @Insert
    suspend fun insertProduct(product: Product)

    // Метод для получения всех продуктов
    @Query("SELECT * FROM products")
    suspend fun getAllProducts(): List<Product>
}
