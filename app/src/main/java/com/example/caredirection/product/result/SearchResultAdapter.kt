package com.example.caredirection.product.result

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.caredirection.R
import kotlinx.android.synthetic.main.activity_product_search_result.view.*

class SearchResultAdapter(private val context: Context): RecyclerView.Adapter<SearchResultHolder>(){
    var data = listOf<RvSearchResultData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rv_product_item_search_result,parent,false)

        return SearchResultHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: SearchResultHolder, position: Int) {
        holder.bind(data[position])
    }

}