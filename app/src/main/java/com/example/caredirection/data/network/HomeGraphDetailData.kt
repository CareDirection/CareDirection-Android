package com.example.caredirection.data.network

data class HomeGraphDetailData(
    val status:Int,
    val message:String,
    val data:List<DetailedGraphItem>
)
data class DetailedGraphItem(
    val nutrient_name :String,
    val my_change_value_description :String,
    val my_current_value_percent:Int,
    val description:String
)