package com.example.caredirection.care_product.search

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.caredirection.R
import com.example.caredirection.data.RvSearchCareProductData

class SearchCareProductAdapter (private val context: Context) :RecyclerView.Adapter<SearchCareProductHolder>(){
    var data = listOf<RvSearchCareProductData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchCareProductHolder{
        val view = LayoutInflater.from(context).inflate(R.layout.rv_survey_search, parent, false)
        return SearchCareProductHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: SearchCareProductHolder, position: Int) {
        holder.bind(data[position])
    }


}