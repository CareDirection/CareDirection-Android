package com.example.caredirection.network

import com.example.caredirection.data.network.*
import retrofit2.Call
import retrofit2.http.*

interface RequestInterface {

    @FormUrlEncoded
    @POST("/users/signin") // uri
    fun postLogin(
        @Field("user_id")user_id : String //category에 String 타입 category 값 전달.
    ) : Call<LoginData>

    //성분학습뷰 -성분 학습 - 은이
    @GET("/nutrient/{nutrient_idx}")
    fun getIngredientInfo(
        @Header("token")token: String,
        @Path("nutrient_idx")nutrient_idx:Int
    ):Call<IngredientData>

    //홈뷰 - 기능성 원료 - 은이
    @GET("/nutrient/function")
    fun getFunctional(
        @Header("token")token: String
    ):Call<HomeFunctionalData>

    //홈뷰 -그래프 가져오기 - 은이
    @GET("/graph")
    fun getHomeGraph(
        @Header("token")token: String
    ):Call<HomeGraphData>
    //성분학습뷰 - 아티클리스트 - 은이
    @GET("/article")
    fun getArticleList(
        @Header("Content-Type") content_Type:String="application/json"
    ):Call<ArticleListData>

}