package com.example.caredirection.home.essential

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.caredirection.R
import com.example.caredirection.data.RvCareProductData
import com.example.caredirection.data.RvEssentialGraphData
import com.example.caredirection.home.care_product.CareProductHolder

class EssentialGraphAdapter(private val context: Context) : RecyclerView.Adapter<EssentialGraphHolder>(){


    var data = listOf<RvEssentialGraphData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EssentialGraphHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rv_item_graph_detail,parent,false)
        return EssentialGraphHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }
    override fun onBindViewHolder(holder: EssentialGraphHolder, position: Int) {
        holder.bind(data[position])
    }

}
