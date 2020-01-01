package com.example.caredirection.data.network

import com.google.gson.annotations.SerializedName

data class LoginData(
    @SerializedName("status")
    val status : Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data : LoginTokenData
)

data class LoginTokenData(
    @SerializedName("token")
    val token : String
)