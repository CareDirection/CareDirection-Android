package com.example.caredirection.data


import com.google.gson.annotations.SerializedName

data class EfficacyStudyResponse(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int
)