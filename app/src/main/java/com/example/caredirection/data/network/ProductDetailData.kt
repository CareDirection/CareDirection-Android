package com.example.caredirection.data.network

data class ProductDetailData(
    val data: List<DetailData>,
    val message: String,
    val status: Int
)
data class DetailData(
    val common_data: CommonData,
    val count_price: CountPrice
)data class CountPrice(
    val product_quantity_count: Int,
    val product_quantity_price: Int
)
data class CommonData(
    val image_key: String,
    val main_nutrient_name: String,
    val product_additives: String,
    val product_cautions: String,
    val product_company_name: String,
    val product_daily_dose: String,
    val product_detail_name: String,
    val product_detail_value: String,
    val product_features_name: String,
    val product_name: String,
    val product_package_type: Int,
    val product_standard1: String,
    val product_standard1_value: String,
    val product_standard2: String,
    val product_standard2_value: String,
    val product_standard3: String,
    val product_standard3_value: String
)