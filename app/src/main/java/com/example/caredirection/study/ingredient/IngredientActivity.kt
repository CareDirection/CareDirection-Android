package com.example.caredirection.study.ingredient

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.caredirection.R
import com.example.caredirection.common.toast
import kotlinx.android.synthetic.main.activity_ingredient.*
import kotlinx.android.synthetic.main.activity_ingredient.view.*
import kotlinx.android.synthetic.main.menu_top_plain_text.view.*


class IngredientActivity : AppCompatActivity() {

    private lateinit var rvIngredientAdapter: IngredientAdapter
    private lateinit var Ingredient : String
    private  var ingredientIdx : Int=0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingredient)
        val fromIngredientIntent = getIntent()
        Ingredient=fromIngredientIntent.getStringExtra("ingredient")

        top_bar_main.top_plain_text.txt_top_bar_title.text = Ingredient

        ingredientIdx=when(Ingredient){
            "홍삼"->0
            "오메가 3"->1
            "밀크씨슬"->2
            "루테인"->3
            "유산균"->4
            "비타민 D"->5
            else->0
        }

        toast(ingredientIdx.toString())
    }
}
