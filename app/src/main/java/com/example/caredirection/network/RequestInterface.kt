package com.example.caredirection.network

import com.example.caredirection.data.network.LoginData
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface RequestInterface {
    @FormUrlEncoded

    @POST("/users/signin") // uri
    fun postLogin(
        @Field("")user_id : String //category에 String 타입 category 값 전달.
    ) : Call<LoginData>


}