package com.example.caredirection.data.network

data class HomCareProductData(
    val status:Int,
    val message:String,
    val data:List<HomCareProductItem>
)
data class HomCareProductItem(
    val product_idx:Int,
    val product_name:String,
    val product_is_dosed:Boolean,
    val image_location:String
)