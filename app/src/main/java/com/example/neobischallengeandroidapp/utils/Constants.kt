package com.example.neobischallengeandroidapp.utils

import com.example.neobischallengeandroidapp.network.ApiFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


// const val BASE_URL = "https://neobook.online/ecobak/"
   object Constants {
       private const val BASE_URL = "https://neobook.online/ecobak/"
       const val GET_PRODUCTS_URL = BASE_URL + "product-list/"

       private val retrofit: Retrofit by lazy {
           Retrofit.Builder()
               .baseUrl(BASE_URL)
               .addConverterFactory(GsonConverterFactory.create())
               .build()
       }

       val apiService: ApiFactory by lazy {
           retrofit.create(ApiFactory::class.java)
       }
   }
