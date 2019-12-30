package com.example.caredirection.product.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.caredirection.R
import com.example.caredirection.product.standard.MainProductAdapter
import kotlinx.android.synthetic.main.activity_product_detail.*

class ActivityProductDetail : AppCompatActivity() {

    private lateinit var rv_product_detail: RecyclerView
    private lateinit var rv_product_detail_adapter : ProductDetailAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        val category = arrayOf("제품","성분")
        val categoryAdapter = ArrayAdapter(this@ActivityProductDetail,R.layout.spinner_product_search_item, category)

        initList()
    }

    //spinner_activity_product_detail_per 스피너 아이디

    private fun initList(){

        rv_product_detail_adapter = ProductDetailAdapter(this@ActivityProductDetail)
        rv_product_detail = findViewById(R.id.rv_product_detail_item)

        rv_product_detail.layoutManager = LinearLayoutManager(this@ActivityProductDetail)
        rv_product_detail_adapter.data = listOf(
            ProductDetailAdapter.RvProductDetailData("쿠팡","16,920 원","(1일 188원)"),
            ProductDetailAdapter.RvProductDetailData("쿠팡", "16,920 원", "(1일 188원)"),
            ProductDetailAdapter.RvProductDetailData("쿠팡","16,920 원","(1일 188원)")
        )

        rv_product_detail.adapter = rv_product_detail_adapter

        val category = arrayOf("30정","90정", "180정")
        val categoryAdapter = ArrayAdapter(this@ActivityProductDetail,R.layout.spinner_product_search_item, category)

        //region 스피너
        categoryAdapter.setDropDownViewResource(R.layout.spinner_product_search_item)
        spinner_activity_product_detail_per.adapter = categoryAdapter

        spinner_activity_product_detail_per.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when (position) {

                }
            }
        }



    }
}
