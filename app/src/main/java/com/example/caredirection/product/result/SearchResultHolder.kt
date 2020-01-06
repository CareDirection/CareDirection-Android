package com.example.caredirection.product.result

import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.caredirection.R
import com.example.caredirection.data.network.ProductSearchContentData
import com.example.caredirection.data.network.Search
import com.example.caredirection.product.detail.ActivityProductDetail
import org.w3c.dom.Text

class SearchResultHolder(view: View): RecyclerView.ViewHolder(view) {


    val img_rv_search_result_item_product: ImageView = view.findViewById(R.id.img_rv_search_result_item_product)
    val txt_rv_search_result_item_ename: TextView = view.findViewById(R.id.txt_rv_search_result_item_ename)
    val txt_rv_search_result_item_publisher: TextView = view.findViewById(R.id.txt_rv_search_result_item_publisher)
    val txt_rv_search_result_item_kname: TextView = view.findViewById(R.id.txt_rv_search_result_item_kname)
    val txt_rv_search_result_item_price: TextView = view.findViewById(R.id.txt_rv_search_result_item_price)
    val txt_rv_search_result_item_perprice: TextView = view.findViewById(R.id.txt_rv_search_result_item_perprice)
    val txt_rv_search_result_item_perstandard: TextView = view.findViewById(R.id.txt_rv_search_result_item_perstandard)

    fun bind(data: Search){
        Glide.with(itemView)
            .load(data.image_key)
            .centerCrop()
            .into(img_rv_search_result_item_product)
        txt_rv_search_result_item_ename.text = data.product_company_name
        txt_rv_search_result_item_kname.text = data.product_name.toString()
        txt_rv_search_result_item_price.text = data.product_quantity_price.toString()
        if(data.product_quantity_count != 0){
            txt_rv_search_result_item_perprice.text = (data.product_quantity_price / data.product_quantity_count).toString()
        }
        if(data.product_is_import == 0){
            txt_rv_search_result_item_publisher.visibility = View.INVISIBLE
        }
        else if(data.product_is_import == 1){
            txt_rv_search_result_item_publisher.visibility = View.VISIBLE
        }
        txt_rv_search_result_item_perstandard.text = data.product_quantity_count.toString()
        itemView.setOnClickListener{
            val intent = Intent(itemView.context, ActivityProductDetail::class.java)

            intent.putExtra("name", data.product_idx.toString())

            startActivity(itemView.context,intent,null)
        }
    }

}