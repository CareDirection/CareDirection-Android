package com.example.caredirection.study.ingredient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.caredirection.R
import kotlinx.android.synthetic.main.activity_ingredient.*
import kotlinx.android.synthetic.main.activity_ingredient.view.*
import kotlinx.android.synthetic.main.menu_top_plain_text.view.*


class IngredientActivity : AppCompatActivity() {

    private lateinit var rvIngredientAdapter: IngredientAdapter
    private lateinit var Ingredient : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingredient)
        val fromIngredientIntent = getIntent()
        Ingredient=fromIngredientIntent.getStringExtra("ingredient")

        top_bar_main.top_plain_text.txt_top_bar_title.text = Ingredient


    }
}
