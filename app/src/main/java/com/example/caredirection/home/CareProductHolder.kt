package com.example.caredirection.home

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.caredirection.R
import com.example.caredirection.data.RvCareProductData
import kotlinx.android.synthetic.main.rv_item_care_home.view.*

class CareProductHolder(view:View) : RecyclerView.ViewHolder(view){
    val imgCareProduct : ImageView = view.findViewById(R.id.img_rv_item_care_product)
    val isCheckedCareProduct : ImageView = view.findViewById(R.id.check_care_daily)
    val nameProduct : TextView = view.findViewById(R.id.txt_care_product)

    fun bind(data:RvCareProductData){
        imgCareProduct.setBackgroundResource(R.color.colorRed)
        isCheckedCareProduct.setImageResource(R.drawable.ic_product_selected)
        nameProduct.text=data.nameProduct

    }
}