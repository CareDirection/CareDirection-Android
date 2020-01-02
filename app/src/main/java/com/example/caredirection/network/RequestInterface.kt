package com.example.caredirection.network

import com.example.caredirection.data.network.*
import com.example.caredirection.product.result.ProductSearchResult
import retrofit2.Call
import retrofit2.http.*
import java.util.logging.Filter

interface RequestInterface {

    @FormUrlEncoded
    @POST("/users/signin") // uri
    fun postLogin(
        @Field("user_id") user_id: String //category에 String 타입 category 값 전달.
    ): Call<LoginData>

    @GET("/nutrient/{nutrient_idx}")
    fun getIngredientInfo(
        @Header("token") token: String,
        @Path("nutrient_idx") nutrient_idx: Int
    ): Call<IngredientData>

    @GET("/product/tablist")
    fun getProductSearchList(
        @Header("token") token: String
    ): Call<ProductSearchData>

    @GET("/search")
    fun getProductContentList(
        @Query("query") query: String,
        @Query("filter") filter: String,
        @Query("limit") limit: Int
    ): Call<ProductSearchContentData>

    @GET("/search")
    fun getProductList(
        @Query("query") query: String,
        @Query("filter") filter: String
    ): Call<ProductSearchContentData>

    @GET("/product/{product_idx}/info")
    fun getProductDetailData(
        @Header("token") token: String,
        @Path("product_idx") product_idx: String
    ): Call<ProductDetailData>

    @GET("/product/{product_idx}/lowprice")
    fun getProductDetailLow(
        @Header("token") token: String,
        @Path("product_idx") product_idx: String
    ): Call<ProductDetailLowest>

    @GET("/product/{product_idx}/standard")
    fun getProductStandard(
        @Header("Content-Type") contentType : String = "application/json",
        @Path("product_idx") product_idx: String
    ):Call<ProductStandardData>

//    asdf.getProductStandard( product_idx = "asdf").enqeuue

}