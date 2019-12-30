package com.example.caredirection.study.ingredient

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.caredirection.R
import com.example.caredirection.data.RvIngredientData

class IngredientHolder(view: View) : RecyclerView.ViewHolder(view){
    val img:ImageView=view.findViewById(R.id.img_rv_item_ingredient_ingredient)
    val txt:TextView=view.findViewById(R.id.txt_rv_item_ingredient_ingredient)

    fun bind(data: RvIngredientData){
        img.setImageResource(data.color)
        txt.text=data.text
    }

}