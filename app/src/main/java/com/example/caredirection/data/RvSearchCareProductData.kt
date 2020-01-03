package com.example.caredirection.data

data class RvSearchCareProductData(
    val img : String,
    val productName :String,
    val company : String,
    val whereBuy : Boolean,
    val price: String,
    val priceADay:String,
    val amountStandardPills:String,
    val managed:Boolean
)