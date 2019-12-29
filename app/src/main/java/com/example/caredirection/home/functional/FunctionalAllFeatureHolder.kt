package com.example.caredirection.home.functional

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.caredirection.R
import com.example.caredirection.data.RvFunctionalAllData

class FunctionalAllFeatureHolder(view: View) : RecyclerView.ViewHolder(view){
    val imgFunctionalAll: ImageView = view.findViewById(R.id.img_rv_item_functional_all)
    val txtFunctionalAll : TextView = view.findViewById(R.id.txt_rv_item_functional_all)

    fun bind(data: RvFunctionalAllData){
        imgFunctionalAll.setBackgroundResource(R.color.colorPrimary)
        txtFunctionalAll.text="간건강"
    }
}