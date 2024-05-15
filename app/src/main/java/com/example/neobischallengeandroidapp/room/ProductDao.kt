package com.example.neobischallengeandroidapp.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.neobischallengeandroidapp.module.Product

@Dao
interface ProductDao {
    @Insert
    suspend fun insertProduct(product: Product)

    @Query("SELECT * FROM products")
    suspend fun getAllProducts(): List<Product>
}