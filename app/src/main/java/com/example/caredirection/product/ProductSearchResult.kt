package com.example.caredirection.product

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.caredirection.R
import kotlinx.android.synthetic.main.activity_product_search_result.*
import kotlinx.android.synthetic.main.fragment_product_search.view.*

class ProductSearchResult : AppCompatActivity() {
    val category = arrayOf("제품","성분")
    val categoryAdapter = ArrayAdapter(this@ProductSearchResult,R.layout.spinner_product_search_item, category)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_search_result)



        //categoryAdapter.setDropDownViewResource(R.layout.fragment_product_search)
        spinner_activity_product_search_result.adapter = categoryAdapter

        spinner_activity_product_search_result.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

            }
        }



    }
}
