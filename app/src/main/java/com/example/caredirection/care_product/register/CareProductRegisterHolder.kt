package com.example.caredirection.care_product.register

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.caredirection.R
import com.example.caredirection.data.RvCareProductRegisterData

class CareProductRegisterHolder(view: View) : RecyclerView.ViewHolder(view),View.OnClickListener{


    val amount : TextView = view.findViewById(R.id.txt_rv_item_care_register_amount)
    val image:ImageView=view.findViewById(R.id.img_rv_item_care_register)
    val company:TextView=view.findViewById(R.id.txt_rv_item_care_register_company)
    val whereBuy:TextView=view.findViewById(R.id.txt_rv_item_care_register_where_buy)
    val productName :TextView=view.findViewById(R.id.txt_rv_item_care_register_product_name)
    val priceView: TextView=view.findViewById(R.id.txt_rv_item_care_register_price)
    val priceADay:TextView=view.findViewById(R.id.txt_rv_item_care_register_price_a_day)
    val amountStandardPillsVIew: TextView = view.findViewById(R.id.txt_rv_item_care_register_amount_standard_pills)


    fun bind(data:RvCareProductRegisterData){

        amount.text= data.amount.toString()+"회 남음"
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
        priceView.text=data.price
        priceADay.text=data.priceADay
        amountStandardPillsVIew.text=data.amountStandardPills

    }
    override fun onClick(v: View?) {
       //todo 다이얼로그 띄우기

    }

}