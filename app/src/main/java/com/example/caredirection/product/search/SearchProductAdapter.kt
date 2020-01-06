package com.example.caredirection.product.search

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.caredirection.R
import com.example.caredirection.data.network.Search
import com.example.caredirection.product.detail.ActivityProductDetail
import com.example.caredirection.product.standard.ActivityProductStandard

class SearchProductAdapter(private val context: Context) :
    RecyclerView.Adapter<SearchProductAdapter.SearchProductHolder>() {
    var item = mutableListOf<Search>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchProductHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.rv_search_item_product, parent, false)

        return SearchProductHolder(view)
    }

    override fun getItemCount(): Int {
        return item.size
    }

    override fun onBindViewHolder(holder: SearchProductHolder, position: Int) {
        holder.bind(item[position])
    }

    inner class SearchProductHolder(view: View) : RecyclerView.ViewHolder(view) {

        val img_rv_search_result_item_product: ImageView =
            view.findViewById(R.id.img_rv_search_result_item_product)
        val txt_rv_search_item_ename: TextView =
            view.findViewById(R.id.txt_rv_search_result_item_ename)
        val txt_rv_search_item_publisher: TextView =
            view.findViewById(R.id.txt_rv_search_result_item_publisher)
        val txt_rv_search_item_kname: TextView =
            view.findViewById(R.id.txt_rv_search_result_item_kname)
        val txt_rv_item_product_price: TextView =
            view.findViewById(R.id.txt_rv_search_result_item_price)
        val txt_rv_search_result_item_perprice: TextView =
            view.findViewById(R.id.txt_rv_search_result_item_perprice)
        val txt_rv_search_result_item_perstandard: TextView =
            view.findViewById(R.id.txt_rv_search_result_item_perstandard)


        fun bind(data: Search) {
            Glide.with(itemView)
                .load(data.image_key)
                .centerCrop()
                .into(img_rv_search_result_item_product)
            txt_rv_search_item_ename.text = data.product_company_name
            if(data.product_is_import == 0){
                txt_rv_search_item_publisher.visibility = View.INVISIBLE
            }
            else if(data.product_is_import == 1){
                txt_rv_search_item_publisher.visibility = View.VISIBLE
            }
            txt_rv_search_item_kname.text = data.product_name
            txt_rv_item_product_price.text = data.product_quantity_price.toString()
            txt_rv_search_result_item_perprice.text = (data.product_quantity_price/data.product_quantity_count).toString()
            txt_rv_search_result_item_perstandard.text = data.product_quantity_count.toString()
            if(data.product_is_import == 0 ){
                txt_rv_search_item_publisher
            }
            itemView.setOnClickListener{
                val intent = Intent(itemView.context, ActivityProductDetail::class.java)

                intent.putExtra("search_product_idx", data.product_idx.toString())
                startActivity(context, intent, null)
            }
        }
    }
}


data class rv_search_item(

    val product_idx: Int,
    val image_key: String,
    val product_name: String,
    val product_company_name: String,
    val product_is_import: Int, //true false라 다시 수정해야함
    var product_quantity_price: Int,
    val product_quantity_count: Int
)
