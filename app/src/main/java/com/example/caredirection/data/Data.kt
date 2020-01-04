package com.example.caredirection.data


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("image_location")
    val imageLocation: String,
    @SerializedName("nutrient_efficacy_comment")
    val nutrientEfficacyComment: String,
    @SerializedName("nutrient_idx")
    val nutrientIdx: Int,
    @SerializedName("nutrient_name")
    val nutrientName: String
)