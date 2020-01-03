package com.example.caredirection.data.network

data class ProductStandardData(
    val data: List<DataX>,
    val message: String,
    val status: Int
)

data class DataX(
    val standard: String,
    val standard_description: String,
    val standard_value: String
)