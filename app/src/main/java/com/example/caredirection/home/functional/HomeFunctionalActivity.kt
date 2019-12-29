package com.example.caredirection.home.functional

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.caredirection.R
import com.example.caredirection.data.RvCareProductData
import com.example.caredirection.data.RvFunctionalAllData
import com.example.caredirection.home.CareProductAdapter
import kotlinx.android.synthetic.main.activity_home_functional.*
import kotlinx.android.synthetic.main.menu_top_plain_text.view.*

class HomeFunctionalActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_functional)

        txt_top_bar_home_functional.txt_top_bar_plain.text="기능성 원료"


        //리사이클러뷰 레이아웃 설정
        rv_functional_all_view.layoutManager = GridLayoutManager(this,3)
        //어댑더 정의
        val rvFunctionalAllAdapter = FunctionalAllFeatureAdapter(this)
        //카드뷰에 어댑터 연결
        rv_functional_all_view.adapter = rvFunctionalAllAdapter

        //더미 데이터 넣어주기
        rvFunctionalAllAdapter.data = listOf(
           RvFunctionalAllData(R.color.colorRed,"간겅강"),
            RvFunctionalAllData(R.color.colorRed,"간겅강"),
            RvFunctionalAllData(R.color.colorRed,"간겅강"),
            RvFunctionalAllData(R.color.colorRed,"간겅강"),
            RvFunctionalAllData(R.color.colorRed,"간겅강"),
            RvFunctionalAllData(R.color.colorRed,"간겅강"),
            RvFunctionalAllData(R.color.colorRed,"간겅강"),
            RvFunctionalAllData(R.color.colorRed,"간겅강"),
            RvFunctionalAllData(R.color.colorRed,"간겅강")
        )

    }
}
