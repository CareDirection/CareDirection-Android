package com.example.caredirection.network

import com.example.caredirection.data.network.*
import retrofit2.Call
import retrofit2.http.*

interface RequestInterface {
    // 로그인 뷰 - 중복확인 - 명희
    @FormUrlEncoded
    @POST("/users/id")
    fun postIdCheck(
        @Field("user_id")user_id : String
    ) : Call<IdData>

    // 로그인 뷰 - 회원가입 - 명희
    @FormUrlEncoded
    @POST("/users/signup")
    fun postSignUp(
        @Field("user_id")user_id : String,
        @Field("user_pw")user_pw : String
    ) : Call<SignUpData>

    // 로그인 뷰 - 로그인 - 명희
    @FormUrlEncoded
    @POST("/users/signin")
    fun postLogin(
        @Field("user_id")user_id : String,
        @Field("user_pw")user_pw : String
    ) : Call<LoginData>

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



}