package com.example.caredirection.home.functional

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.caredirection.R
import com.example.caredirection.data.RvFunctionalAllData

class FunctionalAllFeatureAdapter(private val context: Context) : RecyclerView.Adapter<FunctionalAllFeatureHolder>(){

    var data = listOf<RvFunctionalAllData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FunctionalAllFeatureHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rv_item_functional_all,parent,false)

        return FunctionalAllFeatureHolder(view, context)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: FunctionalAllFeatureHolder, position: Int) {
        holder.bind(data[position])
    }


}