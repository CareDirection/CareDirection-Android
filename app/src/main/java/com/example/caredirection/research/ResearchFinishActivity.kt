package com.example.caredirection.research

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.example.caredirection.R
import com.example.caredirection.care_product.CareProductActivity
import com.example.caredirection.common.logDebug
import com.example.caredirection.common.toast
import com.example.caredirection.home.HomeActivity
import com.example.caredirection.research.DB.ResearchKeeper
import kotlinx.android.synthetic.main.activity_research_finish.*

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

        //질병
        var dRange = IntRange(1, keeper.disease!!.size-1)
        user_survey_item_value1 = keeper.disease.toString()
        user_survey_item_value2 = ""
        user_survey_item_value3 = ""
        user_survey_item_value4 = ""
        user_survey_item_value5 = ""
        user_survey_item_value6 = ""
        user_survey_item_value7 = ""

        toast(user_survey_item_value1)
    }

    // 상태바 배경투명 설정
    fun statusBarHeight(context: Context): Int {
        val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")

        return if (resourceId > 0) context.resources.getDimensionPixelSize(resourceId)
        else 0
    }
}
