package com.example.caredirection.product.standard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.caredirection.R
import kotlinx.android.synthetic.main.activity_product_standard.*
import kotlinx.android.synthetic.main.dialog_product_standard_explation.view.*

class ActivityProductStandard : AppCompatActivity() {

    private lateinit var rv_main_product: RecyclerView
    private lateinit var rv_main_product_adapter : MainProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_standard)

        initList()
        activity_product_detail_item_standard1.setOnClickListener{
            contentDialog()
        }
        img_activity_product_top_filter.setOnClickListener{
            filterDialog()
        }
    }
    private fun initList(){
//1. 어뎁터 데이터에 에드시키기 , 2. 리사이클러뷰 가져오기 , 리사이클러뷰 리니얼 설정, 리사이클러뷰에,adapter에 어뎁터 넣기
        rv_main_product_adapter = MainProductAdapter(this@ActivityProductStandard)
        //리사이클러뷰 가져오기
        rv_main_product = findViewById(R.id.activityt_product_rv)
        //리사이클러뷰에 리니얼로 넣기
        rv_main_product.layoutManager = LinearLayoutManager(this@ActivityProductStandard)
        rv_main_product_adapter.data = mutableListOf(
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


        val dialog = builder.setView(dialogView).show()

        dialogView.txt_dialog_product_standard_close.setOnClickListener{
            dialog.dismiss()
        }
    }
    private fun filterDialog(){
        val builder = AlertDialog.Builder(this)
        val dialogView = layoutInflater.inflate(R.layout.dialog_product_standard_filter, null)


        builder.setView(dialogView)
            .setPositiveButton("확인") { dialogInterface, i ->

            }
            .setNegativeButton("취소") { dialogInterface, i ->

            }
            .show()
    }

}
