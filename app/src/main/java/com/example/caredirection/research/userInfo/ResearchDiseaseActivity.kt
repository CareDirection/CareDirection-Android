package com.example.caredirection.research.userInfo

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.CheckBox
import android.widget.Checkable
import android.widget.CheckedTextView
import com.example.caredirection.R
import com.example.caredirection.common.toast
import com.example.caredirection.research.DB.ResearchKeeper
import kotlinx.android.synthetic.main.activity_research_disease.*

class ResearchDiseaseActivity : AppCompatActivity() {

    private lateinit var disButtons : List<CheckBox>
    private lateinit var keeper :ResearchKeeper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_research_disease)
        keeper = ResearchKeeper(this)

        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        cl_disease.setPadding(0, statusBarHeight(this), 0, 0)

        disButtons = listOf(
            btn_disease_1, btn_disease_2, btn_disease_3, btn_disease_4, btn_disease_5, btn_disease_6, btn_disease_clear
        )

        keeper.disease?.let { set ->
            disButtons
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

        txt_disease_nametitle.text = name + "님께서"

        btn_disease_clear.setOnClickListener {
            disButtons.forEach {
                it.isChecked = false
                it.setTextColor(resources.getColor(R.color.colorWhite))
                checkSelectButton()
            }
        }
        var count : Int = 0
        disButtons.forEachIndexed { index, checkBox ->
            disButtons[index].setOnClickListener{
                count++
                toast("index = " + index)

                btn_disease_clear.isEnabled = false
                checkSelectButton()
            }
        }




        btn_disease_next.setOnClickListener{
//            if (!checkSelectButton()){
//                toast("뭘 고르고 넘기셈")
//                return@setOnClickListener
//            }

            val set = mutableSetOf<String>()
            disButtons
                .filter { it.isChecked }
                .forEach { set.add(it.text.toString()) }
            keeper.disease = set

            val symptom_intent = Intent(this,ResearchSymptomActivity::class.java)
            symptom_intent.putExtra("username",name)

            startActivity(symptom_intent)
        }


    }

//    private fun checkSelectButton(): Boolean{
//        return disButtons.any { it.isChecked }
//    }

    private fun checkSelectButton(){
        if(disButtons.any { it.isChecked }){
            btn_disease_next.isEnabled = true
        }
        else{
            btn_disease_next.isEnabled = false
        }
    }

    // 강조타이틀 설정
    private fun setColorInPartitial(){
        val builder = SpannableStringBuilder(txt_disease_subtitle?.text.toString())

        builder.setSpan(
            ForegroundColorSpan(Color.parseColor("#ebf0b0")),
            5,
            7,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        txt_disease_subtitle?.text = builder
    }
}
