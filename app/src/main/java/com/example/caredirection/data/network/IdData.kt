package com.example.caredirection.data.network

import com.google.gson.annotations.SerializedName

data class IdData(
    @SerializedName("status")
    val status : Int,
    @SerializedName("message")
    val message: String
)
