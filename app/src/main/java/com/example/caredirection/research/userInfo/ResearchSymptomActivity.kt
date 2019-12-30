package com.example.caredirection.research.userInfo

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.WindowManager
import android.widget.CheckBox
import android.widget.CheckedTextView
import com.example.caredirection.R
import com.example.caredirection.common.toast
import com.example.caredirection.research.DB.ResearchKeeper
import com.example.caredirection.research.ResearchChange
import kotlinx.android.synthetic.main.activity_research_symptom.*

class ResearchSymptomActivity : AppCompatActivity() {

    private lateinit var btnSymptoms: List<CheckBox>
    private lateinit var keeper :ResearchKeeper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_research_symptom)
        keeper = ResearchKeeper(this)

        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        cl_symptom.setPadding(0, statusBarHeight(this), 0, 0)

        btnSymptoms = listOf(
            btn_symptom_1, btn_symptom_2, btn_symptom_3, btn_symptom_4, btn_symptom_5, btn_symptom_6, btn_symptom_7, btn_symptom_8, btn_symptom_9, btn_symptom_clear
        )

        keeper.disease?.let { set ->
            btnSymptoms
                .filter { it.text in set }
                .forEach { it.isChecked = true }
        }

        makeController()
        setColorInPartitial()
    }

    // 상태바 배경투명 설정
    fun statusBarHeight(context: Context): Int {
        val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")

        return if (resourceId > 0) context.resources.getDimensionPixelSize(resourceId)
        else 0
    }

    // 사용자 입력받아서 초기화
    private fun makeController(){

        val name = keeper.name

        txt_symptom_nametitle?.text = name + "님께서는"

        btn_symptom_clear.setOnClickListener {
            btnSymptoms
                .forEach { it.isChecked = false }
        }

        btn_symptom_next?.setOnClickListener{
                if (!checkSelectButton()) {
                    toast("나가")
                    return@setOnClickListener
                }

                keeper.symptom = btnSymptoms.filter { it.isChecked }.map { it.text.toString() }.toSet()

                val intent = Intent(this,ResearchChange::class.java)
                intent.putExtra("username",name)

                startActivity(intent)
//            }
        }
    }

    private fun checkSelectButton(): Boolean{
        return btnSymptoms.any { it.isChecked }
    }

    // 강조타이틀 설정
    private fun setColorInPartitial(){
        val builder = SpannableStringBuilder(txt_symptom_subtitle?.text.toString())

        builder.setSpan(
            ForegroundColorSpan(Color.parseColor("#ebf0b0")),
            3,
            5,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        txt_symptom_subtitle?.text = builder
    }
}
