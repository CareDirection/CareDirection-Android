package com.example.caredirection.study.ingredient

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.caredirection.R
import com.example.caredirection.data.RvIngredientData

class IngredientAdapter (private val context: Context):RecyclerView.Adapter<IngredientHolder>(){

    var data= listOf<RvIngredientData>()

    private lateinit var onClick : View.OnClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rv_item_ingredient,parent,false)
        view.setOnClickListener(onClick)
        return IngredientHolder(view)
    }

    override fun getItemCount(): Int {
      return data.size
    }

    override fun onBindViewHolder(holder: IngredientHolder, position: Int) {
        holder.bind(data[position])
    }
    fun setOnClick(l : View.OnClickListener){
        onClick = l
    }
}