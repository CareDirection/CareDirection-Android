package com.example.caredirection.product.standard

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.caredirection.R

class MainProductAdapter(private val context: Context): RecyclerView.Adapter<MainProductAdapter.MainProductHolder>() {
    var data = listOf<main_product_rv_item>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainProductHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rv_item_product_standard,parent,false)

        return MainProductHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MainProductHolder, position: Int) {
        holder.bind(data[position])
    }

    inner class MainProductHolder(view: View): RecyclerView.ViewHolder(view) {
        var item = listOf<MainProductAdapter>()

        val img_rv_item_product: ImageView = view.findViewById(R.id.img_rv_search_item_product)
        val txt_rv_item_product_ename : TextView = view.findViewById(R.id.txt_rv_search_item_product_ename)
        val txt_rv_item_product_publisher: TextView = view.findViewById(R.id.txt_rv__search_item_product_publisher)
        val txt_rv_item_product_kname: TextView = view.findViewById(R.id.txt_rv_search_item_product_kname)
        val txt_rv_item_product_price : TextView = view.findViewById(R.id.txt_rv_search_item_product_price)
        val txt_rv_item_product_perprice: TextView = view.findViewById(R.id.txt_rv_search_item_perprice)
        val txt_rv_item_product_perstandard: TextView = view.findViewById(R.id.txt_rv_search_item_perstandard)


        fun bind(data: main_product_rv_item){
            txt_rv_item_product_ename.text = data.EndName
            txt_rv_item_product_publisher.text = data.Publisher
            txt_rv_item_product_kname.text = data.KorName
            txt_rv_item_product_price.text = data.Price
        }
    }

}

data class main_product_rv_item(
    val EndName: String,
    val Publisher: String,
    val KorName: String,
    val Price: String
)