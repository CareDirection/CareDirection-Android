package com.example.caredirection.product.standard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Adapter
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.caredirection.R
import kotlinx.android.synthetic.main.activity_product_search.*
import kotlinx.android.synthetic.main.dialog_product_standard_explation.view.*

class ActivityProductSearch : AppCompatActivity() {

    private lateinit var rv_main_product: RecyclerView
    private lateinit var rv_main_product_adapter : MainProductAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_search)

        initList()
        activity_product_cardview_standard1_txt.setOnClickListener{
            contentDialog()
        }
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
    private fun contentDialog(){
        val builder = AlertDialog.Builder(this)
        val dialogView = layoutInflater.inflate(R.layout.dialog_product_standard_explation, null)


        builder.setView(dialogView).show()

        dialogView.txt_dialog_product_standard_close.setOnClickListener{
            finish()
        }
    }
}
