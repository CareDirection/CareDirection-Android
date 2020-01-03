package com.example.caredirection.research

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.RadioButton
import com.example.caredirection.R
import com.example.caredirection.care_product.CareProductActivity
import com.example.caredirection.common.logDebug
import com.example.caredirection.common.toast
import com.example.caredirection.common.toastLong
import com.example.caredirection.home.HomeActivity
import com.example.caredirection.research.DB.ResearchKeeper
import kotlinx.android.synthetic.main.activity_research_finish.*
import kotlinx.android.synthetic.main.fragment_life_style_activity.*
import kotlinx.android.synthetic.main.fragment_life_style_alcohol.*
import kotlinx.android.synthetic.main.fragment_life_style_exercise.*
import kotlinx.android.synthetic.main.fragment_life_style_vegetable.*

class ResearchFinishActivity : AppCompatActivity() {

    private lateinit var keeper : ResearchKeeper
    private lateinit var user_survey_item_value1 : String
    private lateinit var user_survey_item_value2 : String
    private lateinit var user_survey_item_value3 : String
    private lateinit var user_survey_item_value4 : String
    private lateinit var user_survey_item_value5 : String
    private lateinit var user_survey_item_value6 : String
    private lateinit var user_survey_item_value7 : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_research_finish)

        keeper = ResearchKeeper(this)

        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        cl_finish.setPadding(0, statusBarHeight(this), 0, 0)

        btn_finish_next.setOnClickListener{
            keeper.researchFinish = 1

            setDatas()

            startActivity(Intent(this, CareProductActivity::class.java))
        }
    }

    private fun setDatas(){

        //질병, 증상
        var dRange = IntRange(1, keeper.disease.toString().length-2)
        var sRange = IntRange(1, keeper.symptom.toString().length-2)
        user_survey_item_value1 = keeper.disease.toString().slice(dRange)
        user_survey_item_value2 = keeper.symptom.toString().slice(sRange)

        keeper.cigarette.toString().logDebug()

        //담배
        when(keeper.cigarette){
            2131361882 -> user_survey_item_value3 = "네"
            2131361883 -> user_survey_item_value3 = "아니오"
        }

        //술
        when(keeper.alcohol){
            2131361884 -> user_survey_item_value4 = "네"
            2131361885 -> user_survey_item_value4 = "아니오"
        }

        //야외활동
        when(keeper.activity){
            2131361876 -> user_survey_item_value5 = "4시간 이상"
            2131361877 -> user_survey_item_value5 = "1시간 ~ 4시간"
            2131361878 -> user_survey_item_value5 = "1시간 이하"
        }

        //녹황색 채소 섭취
        when(keeper.vegetable){
            2131361923 -> user_survey_item_value6 = "7회 이상"
            2131361924 -> user_survey_item_value6 = "5회 ~ 6회"
            2131361925 -> user_survey_item_value6 = "3회 ~ 4회"
            2131361926 -> user_survey_item_value6 = "2회 이하"
        }

        keeper.exercise.toString().logDebug()

        //운동
        when(keeper.exercise){
            2131361899 -> user_survey_item_value7 = "4회 이상"
            2131361900 -> user_survey_item_value7 = "2회 ~ 3회"
            2131361901 -> user_survey_item_value7 = "1회 이하"
        }

        user_survey_item_value1.logDebug()
        user_survey_item_value2.logDebug()
        user_survey_item_value3.logDebug()
        user_survey_item_value4.logDebug()
        user_survey_item_value5.logDebug()
        user_survey_item_value6.logDebug()
        user_survey_item_value7.logDebug()
        toastLong("")

    }

    // 상태바 배경투명 설정
    fun statusBarHeight(context: Context): Int {
        val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")

        return if (resourceId > 0) context.resources.getDimensionPixelSize(resourceId)
        else 0
    }
}
