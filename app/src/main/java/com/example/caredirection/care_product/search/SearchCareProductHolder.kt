package com.example.caredirection.care_product.search

import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.caredirection.R
import com.example.caredirection.care_product.CareProductAddActivity
import com.example.caredirection.data.RvSearchCareProductData
import com.example.caredirection.data.network.CareProductSearchItem

class SearchCareProductHolder(view: View) : RecyclerView.ViewHolder(view),View.OnClickListener {
    val image: ImageView = view.findViewById(R.id.img_rv_item_care_search)
    val productName: TextView = view.findViewById(R.id.txt_rv_item_care_search_product_name)
    val company: TextView = view.findViewById(R.id.txt_rv_item_care_search_company)
    val whereBuy: TextView = view.findViewById(R.id.txt_rv_item_care_search_where_buy)
    val price: TextView = view.findViewById(R.id.txt_rv_item_care_search_price)
    val priceADay: TextView = view.findViewById(R.id.txt_rv_item_care_search_price_a_day)
    val amountStandardPills: TextView = view.findViewById(R.id.txt_rv_item_care_search_amount_standard_pills)


    fun bind(data: RvSearchCareProductData) {
        Glide.with(image)
            .load(data.img)
            .centerCrop()
            .into(image)
        company.text=data.company
        if(data.whereBuy) {
            whereBuy.text= "해외직구"
        }
        else{
            whereBuy.text= ""
        }
        productName.text=data.productName
        price.text=data.price
        priceADay.text=data.priceADay
        amountStandardPills.text=data.amountStandardPills
        if(data.managed){
            //흐림처리
        }
    }

    override fun onClick(p0: View?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

    }

}