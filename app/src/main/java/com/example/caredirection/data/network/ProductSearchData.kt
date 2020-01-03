package com.example.caredirection.data.network

import com.google.gson.annotations.SerializedName

data class ProductSearchData(
    @SerializedName("status")
    val status : Int,

    @SerializedName("message")
    val message : String,

    @SerializedName("data")
    val data : List<ProductTapListItem>
)
data class ProductTapListItem(
    val tab_name : String
)