package com.example.caredirection.product.search

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.caredirection.R
import com.example.caredirection.product.standard.main_product_rv_item
import kotlinx.android.synthetic.main.rv_item_search_nutrient.view.*

class SearchNutrientAdapter(private val context: Context): RecyclerView.Adapter<SearchNutrientHolde>() {

    var data = listOf<search_rv_nutrient_item>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchNutrientHolde {
        val view = LayoutInflater.from(context).inflate(R.layout.rv_item_search_nutrient, parent, false)

        return SearchNutrientHolde(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: SearchNutrientHolde, position: Int) {
        holder.bind(data[position])
    }
}