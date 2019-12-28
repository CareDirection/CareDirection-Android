package com.example.caredirection.product.search

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.widget.CheckedTextView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.caredirection.R

class SearchNutrientHolder(view: View): RecyclerView.ViewHolder(view) {

    val txt_search_nutrient : TextView = view.findViewById(R.id.txt_rv_search_nutrient)
    val selector_rv_item_nutrient: CheckedTextView = view.findViewById(R.id.selector_rv_item_nutrient)
    val txt_rv_search_nutrient : CheckedTextView = view.findViewById(R.id.txt_rv_search_nutrient)
    fun bind(data: search_rv_nutrient_item){
        txt_search_nutrient.text = data.Nutrient

       itemView.setOnClickListener{
           selector_rv_item_nutrient.isChecked = selector_rv_item_nutrient.isChecked != true
           txt_rv_search_nutrient.isChecked = txt_rv_search_nutrient.isChecked != true
       }
    }

}
data class search_rv_nutrient_item(
    val Nutrient : String
)