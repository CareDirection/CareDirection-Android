package com.example.caredirection.product.standard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Adapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.caredirection.R

class ActivityProductSearch : AppCompatActivity() {

    private lateinit var rv_main_product: RecyclerView
    private lateinit var rv_main_product_adapter : MainProductAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_search)

        initList()
    }
    private fun initList(){
//1. 어뎁터 데이터에 에드시키기 , 2. 리사이클러뷰 가져오기 , 리사이클러뷰 리니얼 설정, 리사이클러뷰에,adapter에 어뎁터 넣기
        rv_main_product_adapter = MainProductAdapter(this@ActivityProductSearch)
        //리사이클러뷰 가져오기
        rv_main_product = findViewById(R.id.activityt_product_rv)
        //리사이클러뷰에 리니얼로 넣기
        rv_main_product.layoutManager = LinearLayoutManager(this@ActivityProductSearch)
        rv_main_product_adapter.data = listOf(
            main_product_rv_item("ENGliSH NAME","publisher","KOREA NAME","price"),
            main_product_rv_item("ENGliSH NAME","publisher","KOREA NAME","price"),
            main_product_rv_item("ENGliSH NAME","publisher","KOREA NAME","price"),
            main_product_rv_item("ENGliSH NAME","publisher","KOREA NAME","price"),
            main_product_rv_item("ENGliSH NAME","publisher","KOREA NAME","price"),
            main_product_rv_item("ENGliSH NAME","publisher","KOREA NAME","price")
        )

        //리사이클러뷰 가져오기

        //리사이클러뷰에 어뎁더 써서 연결하기
        rv_main_product.adapter = rv_main_product_adapter

    }
}
