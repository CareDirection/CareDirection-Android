package com.example.caredirection.data

data class RvCareProductRegisterData(
    val amount : Int,
    val img : String,
    val company : String,
    val whereBuy : Boolean,
    val productName :String,
    val price: String,
    val priceADay:String,
    val amountStandardPills:String
)
