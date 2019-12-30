package com.example.caredirection.product.detail

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.caredirection.R

class ProductDetailHolder(view: View): RecyclerView.ViewHolder(view){

   // val img_rv_product_detail_item: ImageView = view.findViewById(R.id.img_product_detail_item)
    val txt_product_detail_item_title: TextView = view.findViewById(R.id.txt_product_detail_item_title)
    val txt_product_detail_item_price: TextView = view.findViewById(R.id.txt_product_detail_item_price)
    val txt_product_detail_item_day_price:TextView = view.findViewById(R.id.txt_product_detail_item_day_price)

    fun bind(data: RvProductDetailData){
        txt_product_detail_item_title.text = data.txt_product_detail_item_title
        txt_product_detail_item_price.text = data.txt_product_detail_item_price
        txt_product_detail_item_day_price.text = data.txt_product_detail_item_day_price
    }
    data class RvProductDetailData(

        val txt_product_detail_item_title: String,
        val txt_product_detail_item_price : String,
        val txt_product_detail_item_day_price: String
    )
}
