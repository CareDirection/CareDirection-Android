package com.example.caredirection.care_product

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.caredirection.R
import com.example.caredirection.data.RvCareProductRegisterData

class CareProductActivity : AppCompatActivity() {

    private lateinit var rvCareRegisterView : RecyclerView
    private lateinit var rvCareRegisterAdapter : CareProductRegisterAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_care_product)



        rvCareRegisterView=findViewById(R.id.rv_care_register_view)

        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(rvCareRegisterView)

        rvCareRegisterView.layoutManager=LinearLayoutManager(this)
        rvCareRegisterAdapter= CareProductRegisterAdapter(this)
        rvCareRegisterView.adapter=rvCareRegisterAdapter
        snapHelper.attachToRecyclerView(rvCareRegisterView)

        rvCareRegisterAdapter.data= listOf(
            RvCareProductRegisterData("10외 남음","","회사","해외직구","뉴트리디데이 프리미엄 오메가 3골드 1100",2222222,300,30),
            RvCareProductRegisterData("10외 남음","","회사","해외직구","이건 유산균이다",2222222,300,30),
            RvCareProductRegisterData("10외 남음","","회사","해외직구","이건 유산균이다",2222222,300,30),
            RvCareProductRegisterData("10외 남음","","회사","해외직구","이건 유산균이다",2222222,300,30)
        )

        rvCareRegisterAdapter.notifyDataSetChanged()


    }
}
