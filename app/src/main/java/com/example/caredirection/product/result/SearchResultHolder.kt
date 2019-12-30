package com.example.caredirection.product.result

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.caredirection.R

class SearchResultHolder(view: View): RecyclerView.ViewHolder(view) {

    val txt_rv_search_result_item_kname: TextView = view.findViewById(R.id.txt_rv_search_result_item_kname)
    val txt_rv_search_result_item_ename: TextView = view.findViewById(R.id.txt_rv_search_result_item_ename)
    val txt_rv_search_result_item_price:TextView = view.findViewById(R.id.txt_rv_search_result_item_price)

    fun bind(data: RvSearchResultData){
        txt_rv_search_result_item_kname.text = data.txt_rv_search_result_item_kname
        txt_rv_search_result_item_ename.text = data.txt_rv_search_result_item_ename
        txt_rv_search_result_item_price.text = data.txt_rv_search_result_item_price
    }

}
data class RvSearchResultData(
    val txt_rv_search_result_item_kname: String,
    val txt_rv_search_result_item_ename : String,
    val txt_rv_search_result_item_price: String
)