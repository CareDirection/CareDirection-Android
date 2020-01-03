package com.example.caredirection.data.network

data class HomeFunctionalData(
    val status : Int,
    val message: String,
    val data : List<FunctionalItem>
)
data class FunctionalItem(
    val nutrient:String,
    val efficacy :List<FunctionalEfficacyItem>
)
data class FunctionalEfficacyItem(
    val efficacy_name:String
)