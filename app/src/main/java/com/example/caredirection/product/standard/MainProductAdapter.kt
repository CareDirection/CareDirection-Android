package com.example.caredirection.product.standard

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.caredirection.R

class MainProductAdapter(private val context: Context): RecyclerView.Adapter<MainProductHolder>() {
    var data = listOf<main_product_rv_item>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainProductHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rv_item_product,parent,false)

        return MainProductHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MainProductHolder, position: Int) {
        holder.bind(data[position])
    }

}