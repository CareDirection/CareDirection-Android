package com.example.caredirection.product.search

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.caredirection.R

class SearchNutrientHolde(view: View): RecyclerView.ViewHolder(view) {

    val txt_search_nutrient :TextView = view.findViewById(R.id.txt_rv_search_nutrient)

    fun bind(data: search_rv_nutrient_item){
        txt_search_nutrient.text = data.Nutrient
    }
}
data class search_rv_nutrient_item(
    val Nutrient : String
)