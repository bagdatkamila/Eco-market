package com.example.neobischallengeandroidapp.module

data class ProductList(
    val page: String,
    val count1: Int,
    val next: String,
    val results: List<DetailModel>
)
