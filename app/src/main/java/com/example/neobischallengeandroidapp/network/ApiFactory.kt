package com.example.neobischallengeandroidapp.network

import com.example.neobischallengeandroidapp.module.CategoryModel
import com.example.neobischallengeandroidapp.module.DetailModel
import com.example.neobischallengeandroidapp.module.ProductList
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiFactory {
    @GET("product-category-list/")
    suspend fun getAllCategories(): List<CategoryModel>

    @GET("product-list/")
    suspend fun getProducts(@Query("category_name") categoryName: String): ProductList

}