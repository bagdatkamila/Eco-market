package com.example.ecomarket.network

import com.example.ecomarket.module.CategoryModel
import com.example.ecomarket.module.DetailModel
import com.example.ecomarket.module.ProductList
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiFactory {
    @GET("product-category-list/")    // Метод для получения списка всех категорий продуктов
    suspend fun getAllCategories(): List<CategoryModel>

    @GET("product-list/")     // Метод для получения списка продуктов по категории
    suspend fun getProducts(@Query("category_name") categoryName: String): ProductList

    @GET("order-list/")
    suspend fun getOrders(): List<DetailModel>
}