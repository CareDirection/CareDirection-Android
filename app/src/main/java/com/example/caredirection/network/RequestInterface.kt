package com.example.caredirection.network

import com.example.caredirection.data.EfficacyStudyResponse
import com.example.caredirection.data.network.*
import retrofit2.Call
import retrofit2.http.*
import java.util.logging.Filter

interface RequestInterface {

    @FormUrlEncoded
    @POST("/users/signin")
    fun postLogin(
        @Field("user_id")user_id : String,
        @Field("user_pw")user_pw : String
    ) : Call<LoginData>

    @FormUrlEncoded
    @POST("/users/signup")
    fun postSignUp(
        @Field("user_id")user_id : String,
        @Field("user_pw")user_pw : String
    ) : Call<SignUpData>

    @FormUrlEncoded
    @POST("/users/id")
    fun postIdCheck(
        @Field("user_id")user_id : String
    ) : Call<IdData>

    //성분학습뷰 - 성분 통신 -은이
    // 설문조사 뷰 - 개인정보 - 명희
    @FormUrlEncoded
    @POST("/users/info")
    fun postInfo(
        @Header("token")token: String,
        @Field("user_name")user_name : String,
        @Field("user_gender")user_gender : Int,
        @Field("user_birth")user_birth : String
    ) : Call<InfoData>

    // 설문조사 뷰 - 라이프사이클 - 명희
    @FormUrlEncoded
    @POST("/users/lifecycle")
    fun postLifeCycle(
        @Field("user_survey_item_value1")user_survey_item_value1 : String,
        @Field("user_survey_item_value2")user_survey_item_value2 : String,
        @Field("user_survey_item_value3")user_survey_item_value3 : String,
        @Field("user_survey_item_value4")user_survey_item_value4 : String,
        @Field("user_survey_item_value5")user_survey_item_value5 : String,
        @Field("user_survey_item_value6")user_survey_item_value6 : String,
        @Field("user_survey_item_value7")user_survey_item_value7 : String
    ) : Call<LifeCycleData>

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

    @GET("\t/article/{article_idx}")
    fun getArticleDetails(
        @Header("Content-Type") content_Type:String="application/json",
        @Path("article_idx")article_idx:Int
    ):Call<ArticleDetailsData>


    @GET("/product/dose/list")
    fun getCareProductList(
        @Header("Content-Type")content_type:String="application/json",
        @Header("token")token:String,
        @Query("date")date:String
    ):Call<HomCareProductData>


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


    @GET("/efficacy/nutrients")
    fun getEfficacyStudyList(
        @Query("query") query: String
    ): Call<EfficacyStudyResponse>
}