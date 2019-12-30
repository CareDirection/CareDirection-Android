package com.example.caredirection.study.ingredient

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.caredirection.R
import com.example.caredirection.data.RvIngredientData
import com.example.caredirection.home.CareProductHolder

class IngredientAdapter (private val context: Context):RecyclerView.Adapter<IngredientHolder>(){

    var data= listOf<RvIngredientData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rv_item_ingredient,parent,false)
        return IngredientHolder(view)
    }

    override fun getItemCount(): Int {
      return data.size
    }

    override fun onBindViewHolder(holder: IngredientHolder, position: Int) {
        holder.bind(data[position])
    }

}