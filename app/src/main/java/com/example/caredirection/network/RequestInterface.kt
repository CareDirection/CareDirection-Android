package com.example.caredirection.network

import com.example.caredirection.data.EfficacyStudyResponse
import com.example.caredirection.data.network.*
import retrofit2.Call
import retrofit2.http.*
import java.util.logging.Filter

interface RequestInterface {

    // 로그인 뷰 - 로그인 - 명희
    @FormUrlEncoded
    @POST("/users/signin")
    fun postLogin(
        @Field("user_id")user_id : String,
        @Field("user_pw")user_pw : String
    ) : Call<LoginData>

    // 로그인 뷰 - 회원가입 - 명희
    @FormUrlEncoded
    @POST("/users/signup")
    fun postSignUp(
        @Field("user_id")user_id : String,
        @Field("user_pw")user_pw : String
    ) : Call<SignUpData>

    // 로그인 뷰 - 중복확인 - 명희
    @FormUrlEncoded
    @POST("/users/id")
    fun postIdCheck(
        @Field("user_id")user_id : String
    ) : Call<IdData>

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
    @POST("/survey/lifecycle")
    fun postLifeCycle(
        @Header("token")token: String,
        @Field("user_survey_item_value1")user_survey_item_value1 : String,
        @Field("user_survey_item_value2")user_survey_item_value2 : String,
        @Field("user_survey_item_value3")user_survey_item_value3 : String,
        @Field("user_survey_item_value4")user_survey_item_value4 : String,
        @Field("user_survey_item_value5")user_survey_item_value5 : String,
        @Field("user_survey_item_value6")user_survey_item_value6 : String,
        @Field("user_survey_item_value7")user_survey_item_value7 : String
    ) : Call<LifeCycleData>

    // 제품등록 뷰 - 상품 검색 - 명희
    @GET("/search/dose/product")
    fun getCareProductSearchList(
        @Header("Content-Type")content_type:String="application/json",
        @Header("token")token:String,
        @Query("query")date:String
    ):Call<CareProductSearchData>

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

    @POST("/product/{product_idx}/dose/check")
    fun postCareProductDose(
        @Header("token")token:String,
        @Path("product_idx")product_idx:Int
    ):Call<CareProductDoseData>

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

    //product -제품 디테일 그래프 -은이
    @GET("/product/{product_idx}/graph")
    fun getProductDetailGraph(
        @Header("token") token:String,
        @Path("product_idx")product_idx:Int
    ):Call<HomeGraphData>

    //home - 그래프 상세보기 - 은이
    @GET("/graph/detailed")
    fun getGreaphDetailed(
        @Header("token")token:String
    ):Call<HomeGraphDetailData>
//    asdf.getProductStandard( product_idx = "asdf").enqeuue


    @GET("/efficacy/nutrients")
    fun getEfficacyStudyList(
        @Query("query") query: String
    ): Call<EfficacyStudyResponse>
}