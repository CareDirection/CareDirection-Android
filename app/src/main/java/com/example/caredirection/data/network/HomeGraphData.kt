package com.example.caredirection.data.network

data class HomeGraphData (
    val status:Int,
    val message:String,
    val data:List<HomeGraphItem>
)

data class HomeGraphItem(
    val nutrient_name :String,
    val nutrient_percent:Int
)