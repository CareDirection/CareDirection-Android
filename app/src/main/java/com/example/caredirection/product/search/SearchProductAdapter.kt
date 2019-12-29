package com.example.caredirection.product.search

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckedTextView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.caredirection.R

class SearchProductAdapter(private val context: Context): RecyclerView.Adapter<SearchProductAdapter.SearchProductHolder>() {
    var item = listOf<rv_search_item>()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchProductHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rv_item_product_standard,parent,false)

        return SearchProductHolder(view)
    }

    override fun getItemCount(): Int {
        return item.size
    }

    override fun onBindViewHolder(holder: SearchProductHolder, position: Int) {
        holder.bind(item[position], position)
    }

    inner class SearchProductHolder(view: View):RecyclerView.ViewHolder(view) {

        val txt_rv_search_item_ename : TextView = view.findViewById(R.id.txt_rv_search_item_product_ename)
        val txt_rv_search_item_publisher: TextView = view.findViewById(R.id.txt_rv__search_item_product_publisher)
        val txt_rv_search_item_kname: TextView = view.findViewById(R.id.txt_rv_search_item_product_kname)
        val txt_rv_item_product_price : TextView = view.findViewById(R.id.txt_rv_search_item_product_price)
        val rv_item_product_indicator: CheckedTextView = view.findViewById(R.id.rv_item_product_indicator)
        val img_rv_item_standard: CheckedTextView = view.findViewById(R.id.img_rv_item_standard)

        fun bind(data: rv_search_item, position: Int){
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
        val Price: String,
        var checked : Boolean
    )

}
