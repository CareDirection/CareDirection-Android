package com.example.caredirection.data.network

data class ProductSearchContentData(
    val data: Data,
    val message: String,
    val status: Int
)
data class Data(
    val searchList: List<Search>,
    val topData: TopData
)
data class Search(
    val image_key: String,
    val product_company_name: String,
    val product_idx: Int,
    val product_is_import: Int,
    val product_name: String,
    val product_quantity_count: Int,
    val product_quantity_price: Int
)
data class TopData(
    val product_idx: Int,
    val product_standard1: String,
    val product_standard1_value: String,
    val product_standard2: String,
    val product_standard2_value: String,
    val product_standard3: String,
    val product_standard3_value: String
)