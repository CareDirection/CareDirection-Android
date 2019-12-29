package com.example.caredirection.care_product

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.caredirection.R
import com.example.caredirection.data.RvCareProductRegisterData
import org.w3c.dom.Text

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

        amount.text=data.amount
        //image
        image.setBackgroundResource(R.color.colorRed)
        company.text=data.company
        whereBuy.text=data.whereBuy
        productName.text=data.productName
        priceView.text=data.price.toString()+"원"
        priceADay.text="(1일 "+data.priceADay.toString()+"원)"
        amountStandardPillsVIew.text=data.amountStandardPills.toString()+"정 기준"

    }
    override fun onClick(v: View?) {
       //todo 다이얼로그 띄우기

    }

}