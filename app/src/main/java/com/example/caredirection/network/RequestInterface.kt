package com.example.caredirection.network

import com.example.caredirection.data.network.ArticleDetailsData
import com.example.caredirection.data.network.ArticleListData
import com.example.caredirection.data.network.IngredientData
import com.example.caredirection.data.network.LoginData
import retrofit2.Call
import retrofit2.http.*

interface RequestInterface {

    @FormUrlEncoded
    @POST("/users/signin") // uri
    fun postLogin(
        @Field("user_id")user_id : String //category에 String 타입 category 값 전달.
    ) : Call<LoginData>

    //성분학습뷰 - 성분 통신 -은이
    @GET("/nutrient/{nutrient_idx}")
    fun getIngredientInfo(
        @Header("token")token: String,
        @Path("nutrient_idx")nutrient_idx:Int
    ):Call<IngredientData>

    //성분학습뷰 - 아티클리스트 - 은이
    @GET("/article")
    fun getArticleList(
        @Header("Content-Type") content_Type:String="application/json"
    ):Call<ArticleListData>

    @GET("\t/article/{article_idx}")
    fun getArticleDetails(
        @Header("Content-Type") content_Type:String="application/json",
        @Path("article_idx")article_idx:Int
    ):Call<ArticleDetailsData>

}