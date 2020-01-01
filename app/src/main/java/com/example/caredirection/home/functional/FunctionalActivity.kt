package com.example.caredirection.home.functional

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.caredirection.R
import com.example.caredirection.data.RvFunctionalAllData
import com.example.caredirection.data.RvFunctionalSelectedData
import kotlinx.android.synthetic.main.activity_functional.*
import kotlinx.android.synthetic.main.menu_top_plain_text.view.*

class FunctionalActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_functional)
        //탑 바
        txt_top_bar_home_functional.txt_top_bar_title.text="기능성 원료"

        //케어받는 기능
        rv_functional_selected_view.layoutManager= LinearLayoutManager(this)
        val rvFunctionalSelectedFeatureAdapter = FunctionalSelectedFeatureAdapter(this!!)
        rv_functional_selected_view.adapter=rvFunctionalSelectedFeatureAdapter
        rvFunctionalSelectedFeatureAdapter.data=listOf(
            RvFunctionalSelectedData(listOf("장건강","피로회복"),"오메가3"),
            RvFunctionalSelectedData(listOf("혈행개선"),""),
            RvFunctionalSelectedData(listOf("장건강","피로회복","눈건강"),"프로폴리스"),
            RvFunctionalSelectedData(listOf("피로회복","뼈","장건강"),"오메가3"),
            RvFunctionalSelectedData(listOf("운동보조","두뇌활동"),"홍삼")
        )


        //전체기능리사이클러뷰 레이아웃 설정
        rv_functional_all_view.layoutManager = GridLayoutManager(this,3)
        //어댑더 정의
        val rvFunctionalAllAdapter = FunctionalAllFeatureAdapter(this!!)
        //뷰에 어댑터 연결
        rv_functional_all_view.adapter = rvFunctionalAllAdapter
        //더미 데이터 넣어주기
        rvFunctionalAllAdapter.data = listOf(
            RvFunctionalAllData(R.drawable.btn_liver,"간겅강"),
            RvFunctionalAllData(R.drawable.btn_fatigue_recovery,"피로회복"),
            RvFunctionalAllData(R.drawable.btn_eye,"눈건강"),
            RvFunctionalAllData(R.drawable.btn_blood,"혈행개선"),
            RvFunctionalAllData(R.drawable.btn_immunity,"면역력 활성화"),
            RvFunctionalAllData(R.drawable.btn_digestive,"소화기능"),
            RvFunctionalAllData(R.drawable.btn_brain,"두뇌활동"),
            RvFunctionalAllData(R.drawable.btn_muscle,"운동보조"),
            RvFunctionalAllData(R.drawable.btn_bone,"뼈")
        )
    }

}
