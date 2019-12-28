package com.example.caredirection.product.search

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.caredirection.R

class SearchNutrientAdapter(private val context: Context): RecyclerView.Adapter<SearchNutrientHolder>() {

    var data = listOf<search_rv_nutrient_item>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchNutrientHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rv_item_search_nutrient, parent, false)

        return SearchNutrientHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: SearchNutrientHolder, position: Int) {
        holder.bind(data[position])
    }

}