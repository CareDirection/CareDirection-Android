package com.example.caredirection.home.care_product

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.caredirection.R
import com.example.caredirection.data.RvCareProductData
import kotlinx.android.synthetic.main.rv_item_care_home.view.*

class CareProductHolder(private val view:View) : RecyclerView.ViewHolder(view){
    val imgCareProduct : ImageView = view.findViewById(R.id.img_rv_item_care_product)
    val isCheckedCareProduct : ImageView = view.findViewById(R.id.check_care_daily)
    val nameProduct : TextView = view.findViewById(R.id.txt_care_product)

    fun bind(data:RvCareProductData){
        Glide.with(view)
            .load(data.imgCareProduct)
            .centerCrop()
            .into(imgCareProduct)
        isCheckedCareProduct.setImageResource(R.drawable.selector_home_care_product)
        isCheckedCareProduct.isSelected = data.isCheckedCareProduct
        nameProduct.text=data.nameProduct

    }
}