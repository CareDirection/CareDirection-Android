package com.example.caredirection.login

import android.content.Context
import android.content.SharedPreferences

object TokenController {

    val ACCESS_TOKEN="user_token"

    fun setAccessToken(ctx: Context, token:String){
        val  preference: SharedPreferences =ctx.getSharedPreferences(ACCESS_TOKEN,Context.MODE_PRIVATE)
        val  editor:SharedPreferences.Editor=preference.edit()
        editor.putString("access_token",token)
        editor.commit()
    }
    fun getAccessToken(ctx:Context): String? {
        val preference: SharedPreferences = ctx.getSharedPreferences(ACCESS_TOKEN, Context.MODE_PRIVATE)
        return preference.getString("access_token", "")
    }
}