package com.example.caredirection.product.search

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.caredirection.R
import com.example.caredirection.product.standard.MainProductHolder
import com.example.caredirection.product.standard.main_product_rv_item

class SearchProductAdapter(private val context: Context): RecyclerView.Adapter<SearchProductHoler>() {
    var data = listOf<rv_search_item>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchProductHoler {
        val view = LayoutInflater.from(context).inflate(R.layout.rv_item_product,parent,false)

        return SearchProductHoler(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: SearchProductHoler, position: Int) {
        holder.bind(data[position])
    }
}
