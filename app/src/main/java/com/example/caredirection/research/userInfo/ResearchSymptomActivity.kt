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
import kotlinx.android.synthetic.main.activity_research_gender.*
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
            btn_symptom_1, btn_symptom_2, btn_symptom_3, btn_symptom_4, btn_symptom_5, btn_symptom_6, btn_symptom_7, btn_symptom_8, btn_symptom_9
        )

        keeper.disease?.let { set ->
            btnSymptoms
                .filter { it.text in set }
                .forEach {
                    it.isChecked = true
                    it.setTextColor(resources.getColor(R.color.colorPrimary))
                    checkSelectButton()
                }
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
            if(btn_symptom_clear.isChecked){
                checkBtnColor(btn_symptom_clear,true)
                btnSymptoms.forEach {
                    checkBtnColor(it,false)
                }
            }
            else {
                checkBtnColor(btn_symptom_clear,false)
            }
            checkSelectButton()
        }

        btnSymptoms.forEachIndexed { index, checkBox ->
            btnSymptoms[index].setOnClickListener{
                if(btnSymptoms[index].isChecked){
                    checkBtnColor(btnSymptoms[index],true)
                    checkBtnColor(btn_symptom_clear,false)
                }
                else{
                    checkBtnColor(btnSymptoms[index],false)
                }
                checkSelectButton()
            }
        }

        btn_symptom_next?.setOnClickListener{
                keeper.symptom = btnSymptoms.filter { it.isChecked }.map { it.text.toString() }.toSet()

                val intent = Intent(this,ResearchChange::class.java)
                startActivity(intent)
//            }
        }
    }
    private fun checkBtnColor(checkBox: CheckBox, boolean: Boolean){
        if(boolean){
            checkBox.isChecked = true
            checkBox.setTextColor(resources.getColor(R.color.colorPrimary))
        }
        else{
            checkBox.isChecked = false
            checkBox.setTextColor(resources.getColor(R.color.colorWhite))
        }
    }

    private fun checkSelectButton(){
        if(btnSymptoms.any { it.isChecked }||btn_symptom_clear.isChecked){
            btn_symptom_next.isEnabled = true
        }
        else{
            btn_symptom_next.isEnabled = false
        }
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
