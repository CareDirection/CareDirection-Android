package com.example.caredirection.network

import com.example.caredirection.data.network.IdData
import com.example.caredirection.data.network.IngredientData
import com.example.caredirection.data.network.LoginData
import com.example.caredirection.data.network.SignUpData
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
    @POST("/users/signin") // uri
    fun postLogin(
        @Field("user_id")user_id : String,
        @Field("user_pw")user_pw : String
    ) : Call<LoginData>

    @GET("/nutrient/{nutrient_idx}")
    fun getIngredientInfo(
        @Header("token")token: String,
        @Path("nutrient_idx")nutrient_idx:Int
    ):Call<IngredientData>



}