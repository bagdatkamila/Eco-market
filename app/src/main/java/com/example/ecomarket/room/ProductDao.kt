package com.example.ecomarket.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
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


    @Delete
    suspend fun deleteProduct(product: Product)

    @Update
    suspend fun updateProduct(product: Product)

}
