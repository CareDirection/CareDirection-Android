package com.example.caredirection.network

import com.example.caredirection.data.network.IngredientData
import com.example.caredirection.data.network.LoginData
import com.example.caredirection.data.network.ProductSearchData
import com.example.caredirection.product.result.ProductSearchResult
import retrofit2.Call
import retrofit2.http.*

interface RequestInterface {

    @FormUrlEncoded
    @POST("/users/signin") // uri
    fun postLogin(
        @Field("user_id")user_id : String //category에 String 타입 category 값 전달.
    ) : Call<LoginData>

    @GET("/nutrient/{nutrient_idx}")
    fun getIngredientInfo(
        @Header("token")token: String,
        @Path("nutrient_idx")nutrient_idx:Int
    ):Call<IngredientData>

    @GET("/product/tablist")
    fun getProductSearchList(
        @Header("token")token: String
    ): Call<ProductSearchData>

}