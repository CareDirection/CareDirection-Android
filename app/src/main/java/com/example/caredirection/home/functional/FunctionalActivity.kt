package com.example.caredirection.home.functional

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.caredirection.R
import com.example.caredirection.common.logDebug
import com.example.caredirection.data.RvFunctionalAllData
import com.example.caredirection.data.RvFunctionalSelectedData
import com.example.caredirection.data.network.HomeFunctionalData
import com.example.caredirection.network.RequestURL
import kotlinx.android.synthetic.main.activity_functional.*
import kotlinx.android.synthetic.main.menu_top_plain_text.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FunctionalActivity : AppCompatActivity() {

    private lateinit var rvFunctionalSelectedFeatureAdapter:FunctionalSelectedFeatureAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_functional)
        //탑 바
        txt_top_bar_home_functional.txt_top_bar_title.text="기능성 원료"

        //케어받는 기능
        rv_functional_selected_view.layoutManager= LinearLayoutManager(this)
        rvFunctionalSelectedFeatureAdapter = FunctionalSelectedFeatureAdapter(this)
        rv_functional_selected_view.adapter=rvFunctionalSelectedFeatureAdapter
//        rvFunctionalSelectedFeatureAdapter.data=arrayOf(
//            RvFunctionalSelectedData(arrayOf("장건강","피로회복"),"오메가3"),
//            RvFunctionalSelectedData(arrayOf("혈행개선"),""),
//            RvFunctionalSelectedData(arrayOf("장건강","피로회복","눈건강"),"프로폴리스"),
//            RvFunctionalSelectedData(arrayOf("피로회복","뼈","장건강"),"오메가3"),
//            RvFunctionalSelectedData(arrayOf("운동보조","두뇌활동"),"홍삼")
//        )
        getFunctionalResponse()


        //전체기능리사이클러뷰 레이아웃 설정
        rv_functional_all_view.layoutManager = GridLayoutManager(this,3)
        //어댑더 정의
        val rvFunctionalAllAdapter = FunctionalAllFeatureAdapter(this)
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
    //홈뷰 - 기능성 원료 통신
    private fun getFunctionalResponse() {
        val call: Call<HomeFunctionalData> =
            RequestURL.service.getFunctional("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkeCI6NjQsImlhdCI6MTU3ODAyODgxOCwiZXhwIjo4Nzk3ODAyODgxOCwiaXNzIjoiY2FyZS1kaXJlY3Rpb24ifQ.eR-912HpB7B9JCaYwUlkaGBEphLywOoRCyT4ZZB1DMI")
        call.enqueue(
            object : Callback<HomeFunctionalData> {
                override fun onFailure(call: Call<HomeFunctionalData>, t: Throwable) {
                    t.toString().logDebug()
                }

                override fun onResponse(
                    call: Call<HomeFunctionalData>,
                    response: Response<HomeFunctionalData>
                ) {
                    //TODO recycler view에 띄우기
                    //
                    val functionalRespo: HomeFunctionalData = response.body()!!
                    val funtionalItem =
                        functionalRespo.data//리써이클러뷰의 아이템 //전역변수여야 하나? 여기서 어댑터로 연결해주면 될듯

                    val rvItem = Array(funtionalItem.size) {
                        RvFunctionalSelectedData(
                            arrayOf("", ""), ""
                        )
                    }

                    for (i in funtionalItem.indices) {

                        val efficacyList = funtionalItem[i].efficacy
                        val tempEfficacy = Array(efficacyList.size) { "" }

                        for (j in efficacyList.indices) {
                            //  var tempEfficacy=List<String>(efficacyList.size,{""})
                            tempEfficacy[j] = efficacyList[j].efficacy_name

                            val tempRvItem =
                                RvFunctionalSelectedData(tempEfficacy, funtionalItem[i].nutrient)
                            rvItem[i] = tempRvItem
                        }
                        rvFunctionalSelectedFeatureAdapter.data = rvItem
                        rvFunctionalSelectedFeatureAdapter.notifyDataSetChanged()


                    }
                }

            }
        )

    }

}
