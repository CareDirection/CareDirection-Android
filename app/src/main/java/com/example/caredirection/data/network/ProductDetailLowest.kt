package com.example.caredirection.data.network

data class ProductDetailLowest(
    val data: List<DetailLowestData>,
    val message: String,
    val status: Int
)
data class DetailLowestData(
    val image: String,
    val link: String,
    val lprice: String,
    val mallName: String
)