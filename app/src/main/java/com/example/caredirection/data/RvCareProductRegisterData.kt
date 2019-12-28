package com.example.caredirection.data

data class RvCareProductRegisterData(
    val amount : String,
    val img : String,
    val company : String,
    val whereBuy : String,
    val productName :String,
    val price: Int,
    val priceADay:Int,
    val amountStandardPills:Int
)
