package com.example.ecomarket.network

import com.example.ecomarket.module.CategoryModel
import com.example.ecomarket.module.ProductList
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiFactory {
    @GET("product-category-list/")
    suspend fun getAllCategories(): List<CategoryModel>

    @GET("product-list/")
    suspend fun getProducts(@Query("category_name") categoryName: String): ProductList

}