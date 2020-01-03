package com.example.caredirection.login

import android.content.Context

object LoginController{
    private const val ID_KEY = "id"
    private const val PW_KEY="pw"

    fun getID(context: Context): String{
        val sharedPreferences = context.getSharedPreferences(ID_KEY,Context.MODE_PRIVATE)
        return sharedPreferences.getString(ID_KEY,"")?:""
    }

    fun setID(context: Context, id: String){
        val sharedPreferences = context.getSharedPreferences(ID_KEY,Context.MODE_PRIVATE)
        sharedPreferences
            .edit()
            .putString(ID_KEY,id)
            .apply()
    }

    fun clearID(context: Context){
        val sharedPreferences = context.getSharedPreferences(ID_KEY,Context.MODE_PRIVATE)
        sharedPreferences
            .edit()
            .clear()
            .apply()
    }

    fun getPW(context: Context): String{
        val sharedPreferences = context.getSharedPreferences(PW_KEY,Context.MODE_PRIVATE)
        return sharedPreferences.getString(PW_KEY,"")?:""
    }

    fun setPW(context: Context, pw: String){
        val sharedPreferences = context.getSharedPreferences(PW_KEY,Context.MODE_PRIVATE)
        sharedPreferences
            .edit()
            .putString(PW_KEY,pw)
            .apply()
    }

    fun clearPW(context: Context){
        val sharedPreferences = context.getSharedPreferences(PW_KEY,Context.MODE_PRIVATE)
        sharedPreferences
            .edit()
            .clear()
            .apply()
    }
}