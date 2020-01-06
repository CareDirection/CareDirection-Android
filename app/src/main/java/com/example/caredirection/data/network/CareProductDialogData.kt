package com.example.caredirection.data.network

data class CareProductDialogData(
    val status:Int,
    val message:String,
    val data:List<CareProductDialogItem>
)
data class CareProductDialogItem(
    val image_key:String,
    val product_name:String,
    val product_daily_dose:String
)