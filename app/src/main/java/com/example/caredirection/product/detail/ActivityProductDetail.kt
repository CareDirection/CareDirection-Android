package com.example.caredirection.product.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.caredirection.R
import com.example.caredirection.product.standard.MainProductAdapter

class ActivityProductDetail : AppCompatActivity() {

    private lateinit var rv_product_detail: RecyclerView
    private lateinit var rv_product_detail_adapter : ProductDetailAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        initList()
    }
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

    }
}
