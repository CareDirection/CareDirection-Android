package com.example.caredirection.product.detail

import android.content.Context
import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.caredirection.R
import com.example.caredirection.data.network.DetailLowestData
import com.example.caredirection.data.network.ProductDetailLowest

class ProductDetailAdapter(private val context: Context): RecyclerView.Adapter<ProductDetailAdapter.ProductDetailHolder>() {
    var data = mutableListOf<DetailLowestData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductDetailHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rv_item_product_detail,parent,false)

        return ProductDetailHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ProductDetailHolder, position: Int) {
        holder.bind(data[position])
    }


    inner class ProductDetailHolder(view: View): RecyclerView.ViewHolder(view){
       val img_product_detail_item : ImageView = view.findViewById(R.id.img_product_detail_item)
        val txt_product_detail_item_title: TextView = view.findViewById(R.id.txt_product_detail_item_title)
        val txt_product_detail_item_price: TextView = view.findViewById(R.id.txt_product_detail_item_price)

        fun bind(data: DetailLowestData){
            Glide.with(context)
                .load(data.image)
                .centerCrop()
                .into(img_product_detail_item)
            txt_product_detail_item_title.text = data.mallName
            txt_product_detail_item_price.text = data.lprice
            itemView.setOnClickListener{
                val intent = Intent(itemView.context, ActivityProductDetailWeb::class.java)

                intent.putExtra("link", data.link)
                startActivity(context, intent, null)
            }
        }

    }
}
