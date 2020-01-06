package com.example.caredirection.data.network

data class IngredientData(
    val status : Int,
    val message : String,
    val data:List<IngredientInfoItem>
)
data class IngredientInfoItem(
    val nutrient_common_description :String,
    val image_key:String
)
