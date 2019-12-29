package com.example.caredirection.product.search

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.caredirection.R
import com.example.caredirection.product.standard.main_product_rv_item

class SearchProductHoler(view: View):RecyclerView.ViewHolder(view) {

//    val img_rv_item_product: ImageView = view.findViewById(R.id.img_rv_search_item_product)
    val txt_rv_search_item_ename : TextView = view.findViewById(R.id.txt_rv_search_item_product_ename)
    val txt_rv_search_item_publisher: TextView = view.findViewById(R.id.txt_rv__search_item_product_publisher)
    val txt_rv_search_item_kname: TextView = view.findViewById(R.id.txt_rv_search_item_product_kname)
    val txt_rv_item_product_price : TextView = view.findViewById(R.id.txt_rv_search_item_product_price)
/*    val txt_rv_item_product_perprice: TextView = view.findViewById(R.id.txt_rv_search_item_perprice)
    val txt_rv_item_product_perstandard: TextView = view.findViewById(R.id.txt_rv_search_item_perstandard)*/

    fun bind(data: rv_search_item){
        txt_rv_search_item_ename.text = data.EndName
        txt_rv_search_item_publisher.text = data.Publisher
        txt_rv_search_item_kname.text = data.KorName
        txt_rv_item_product_price.text = data.Price
    }
}
data class rv_search_item(
    val EndName: String,
    val Publisher: String,
    val KorName: String,
    val Price: String
)