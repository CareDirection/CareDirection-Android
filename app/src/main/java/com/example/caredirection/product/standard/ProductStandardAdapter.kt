package com.example.caredirection.product.standard

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckedTextView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.caredirection.R

class MainProductAdapter(private val context: Context): RecyclerView.Adapter<MainProductAdapter.MainProductHolder>() {
    var data = mutableListOf<main_product_rv_item>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainProductHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rv_item_product_standard,parent,false)

        return MainProductHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MainProductHolder, position: Int) {
        holder.bind(data[position], position)
    }

    inner class MainProductHolder(view: View): RecyclerView.ViewHolder(view) {
        var item = listOf<MainProductAdapter>()

        val img_rv_item_product: ImageView = view.findViewById(R.id.img_rv_standard_item_product)
        val txt_rv_item_product_ename : TextView = view.findViewById(R.id.txt_rv_standard_item_product_ename)
        val txt_rv_item_product_publisher: TextView = view.findViewById(R.id.txt_rv_standard_item_product_publisher)
        val txt_rv_item_product_kname: TextView = view.findViewById(R.id.txt_rv_standard_item_product_kname)
        val txt_rv_item_product_price : TextView = view.findViewById(R.id.txt_rv_standard_item_product_price)
        val txt_rv_item_product_perprice: TextView = view.findViewById(R.id.txt_rv_standard_item_perprice)
        val txt_rv_item_product_perstandard: TextView = view.findViewById(R.id.txt_rv_standard_item_perstandard)

        val rv_item_product_indicator:CheckedTextView = view.findViewById(R.id.rv_standard_item_product_indicator)
        val img_rv_item_standard: CheckedTextView = view.findViewById(R.id.img_rv_standard_item_filter)

        /*fun bind(position: Int){
            val item = data[position]
            txt_search_nutrient.text = item.nutrient
            selector_rv_item_nutrient.isChecked = item.check
            txt_rv_search_nutrient.isChecked = item.check

            itemView.setOnClickListener{
                (0 until data.size).forEach {
                    data[it] = data[it].copy(check = false)
                }
                data[position] = data[position].copy(check = true)
                rv_search_nutirient_adapter.notifyDataSetChanged()
            }*/

        fun bind(item: main_product_rv_item, position: Int){
            txt_rv_item_product_ename.text = item.EndName
            txt_rv_item_product_publisher.text = item.Publisher
            txt_rv_item_product_kname.text = item.KorName
            txt_rv_item_product_price.text = item.Price
            rv_item_product_indicator.isChecked = item.Check
            img_rv_item_standard.isChecked = item.Check


            itemView.setOnClickListener{
                (0 until data.size).forEach {
                    data[it] = data[it].copy(Check = false)
                }
                data[position] = data[position].copy(Check = true)
                notifyDataSetChanged()
            }
        }
    }

}

data class main_product_rv_item(
    val EndName: String,
    val Publisher: String,
    val KorName: String,
    val Price: String,
    val Check: Boolean = false
)