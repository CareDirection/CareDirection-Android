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
    val txtlist= listOf("간건강","피로회복","눈건강","혈행개선","면역력 황산화","소화기능","두뇌활동","운동보조","뼈")
    fun bind(data: RvFunctionalAllData){
        imgFunctionalAll.setImageResource(data.img)
        txtFunctionalAll.text=data.txt
    }
}