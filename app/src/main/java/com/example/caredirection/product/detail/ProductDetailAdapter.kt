package com.example.caredirection.product.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.caredirection.ActivityProductDetailWeb
import com.example.caredirection.R
import com.example.caredirection.product.standard.main_product_rv_item

class ProductDetailAdapter(private val context: Context): RecyclerView.Adapter<ProductDetailAdapter.ProductDetailHolder>() {
    var data = listOf<RvProductDetailData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductDetailHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rv_item_product_detail,parent,false)

        return ProductDetailHolder(view, context)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ProductDetailHolder, position: Int) {
        holder.bind(data[position])
    }


    inner class ProductDetailHolder(view: View, context: Context): RecyclerView.ViewHolder(view){

        val txt_product_detail_item_title: TextView = view.findViewById(R.id.txt_product_detail_item_title)
        val txt_product_detail_item_price: TextView = view.findViewById(R.id.txt_product_detail_item_price)
        val txt_product_detail_item_day_price: TextView = view.findViewById(R.id.txt_product_detail_item_day_price)

        fun bind(data: RvProductDetailData){
            txt_product_detail_item_title.text = data.txt_product_detail_item_title
            txt_product_detail_item_price.text = data.txt_product_detail_item_price
            txt_product_detail_item_day_price.text = data.txt_product_detail_item_day_price

            itemView.setOnClickListener{
                val intent = Intent(context, ActivityProductDetailWeb::class.java)

                startActivity(context, intent, null)
            }
        }

    }
    data class RvProductDetailData(

        val txt_product_detail_item_title: String,
        val txt_product_detail_item_price : String,
        val txt_product_detail_item_day_price: String
    )
}
