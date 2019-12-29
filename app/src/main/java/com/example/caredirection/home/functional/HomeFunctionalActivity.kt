package com.example.caredirection.home.functional

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.caredirection.R
import com.example.caredirection.data.RvFunctionalAllData
import com.example.caredirection.data.RvFunctionalSelectedData
import kotlinx.android.synthetic.main.activity_home_functional.*
import kotlinx.android.synthetic.main.menu_top_plain_text.view.*

class HomeFunctionalActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_functional)

        txt_top_bar_home_functional.txt_top_bar_plain.text="기능성 원료"


        //전체기능리사이클러뷰 레이아웃 설정
        rv_functional_all_view.layoutManager = GridLayoutManager(this,3)
        //어댑더 정의
        val rvFunctionalAllAdapter = FunctionalAllFeatureAdapter(this)
        //뷰에 어댑터 연결
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

        //케어받는 기능
        rv_functional_selected_view.layoutManager=LinearLayoutManager(this)
        val rvFunctionalSelectedFeatureAdapter = FunctionalSelectedFeatureAdapter(this)
        rv_functional_selected_view.adapter=rvFunctionalSelectedFeatureAdapter
        rvFunctionalSelectedFeatureAdapter.data=listOf(
           RvFunctionalSelectedData(listOf("장건강"),"오메가 쓰리")
        )


    }
}
