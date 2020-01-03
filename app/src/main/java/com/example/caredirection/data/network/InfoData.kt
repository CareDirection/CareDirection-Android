package com.example.caredirection.data.network

import com.google.gson.annotations.SerializedName

data class InfoData(
    @SerializedName("status")
    val status : Int,
    @SerializedName("message")
    val message: String
)