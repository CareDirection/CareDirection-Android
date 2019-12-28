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
import com.example.caredirection.R
import kotlinx.android.synthetic.main.activity_research_disease.*

class ResearchDiseaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_research_disease)

        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        cl_disease.setPadding(0, statusBarHeight(this), 0, 0)

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

        val name: String = intent.getStringExtra("username")

        txt_disease_nametitle.text = name + "님께서"

//        edt_username?.addTextChangedListener(object : TextWatcher{
//            override fun afterTextChanged(p0: Editable?) {
//                toast("입력")
//            }
//        })

        btn_disease_next.setOnClickListener{
            //            val name = edt_username?.text.toString()
//
//            // 이름 빈칸일 경우,
//            if(name.isEmpty()){
//                toast("입력")
//            }
//            else{
//                //btn_name_next.
//                //btn_name_next.setBackgroundResource(R.drawable.yellow_border)
//
            val symptom_intent = Intent(this,ResearchSymptomActivity::class.java)
            symptom_intent.putExtra("username",name)

            startActivity(symptom_intent)
//            }
        }

        btn_disease_1.setOnClickListener{
            if(btn_disease_1.isSelected) {
                btn_disease_1.isSelected = false
                btn_disease_1.setTextColor(resources.getColor(R.color.colorWhite))
            }
            else {
                btn_disease_1.isSelected = true
                btn_disease_7.isSelected = false
                btn_disease_7.setTextColor(resources.getColor(R.color.colorWhite))
                btn_disease_1.setTextColor(resources.getColor(R.color.colorPrimary))
            }
            checkSelectButton()
        }
        btn_disease_2.setOnClickListener{
            if(btn_disease_2.isSelected) {
                btn_disease_2.isSelected = false
                btn_disease_2.setTextColor(resources.getColor(R.color.colorWhite))
            }
            else {
                btn_disease_2.isSelected = true
                btn_disease_7.isSelected = false
                btn_disease_7.setTextColor(resources.getColor(R.color.colorWhite))
                btn_disease_2.setTextColor(resources.getColor(R.color.colorPrimary))
            }
            checkSelectButton()
        }
        btn_disease_3.setOnClickListener{
            if(btn_disease_3.isSelected) {
                btn_disease_3.isSelected = false
                btn_disease_3.setTextColor(resources.getColor(R.color.colorWhite))
            }
            else {
                btn_disease_3.isSelected = true
                btn_disease_7.isSelected = false
                btn_disease_7.setTextColor(resources.getColor(R.color.colorWhite))
                btn_disease_3.setTextColor(resources.getColor(R.color.colorPrimary))
            }
            checkSelectButton()
        }
        btn_disease_4.setOnClickListener{
            if(btn_disease_4.isSelected) {
                btn_disease_4.isSelected = false
                btn_disease_4.setTextColor(resources.getColor(R.color.colorWhite))
            }
            else {
                btn_disease_4.isSelected = true
                btn_disease_7.isSelected = false
                btn_disease_7.setTextColor(resources.getColor(R.color.colorWhite))
                btn_disease_4.setTextColor(resources.getColor(R.color.colorPrimary))
            }
            checkSelectButton()
        }
        btn_disease_5.setOnClickListener{
            if(btn_disease_5.isSelected) {
                btn_disease_5.isSelected = false
                btn_disease_5.setTextColor(resources.getColor(R.color.colorWhite))
            }
            else {
                btn_disease_5.isSelected = true
                btn_disease_7.isSelected = false
                btn_disease_7.setTextColor(resources.getColor(R.color.colorWhite))
                btn_disease_5.setTextColor(resources.getColor(R.color.colorPrimary))
            }
            checkSelectButton()
        }
        btn_disease_6.setOnClickListener{
            if(btn_disease_6.isSelected) {
                btn_disease_6.isSelected = false
                btn_disease_6.setTextColor(resources.getColor(R.color.colorWhite))
            }
            else {
                btn_disease_6.isSelected = true
                btn_disease_7.isSelected = false
                btn_disease_7.setTextColor(resources.getColor(R.color.colorWhite))
                btn_disease_6.setTextColor(resources.getColor(R.color.colorPrimary))
            }
            checkSelectButton()
        }
        btn_disease_7.setOnClickListener{
            if(btn_disease_7.isSelected) {
                btn_disease_7.isSelected = false
                btn_disease_7.setTextColor(resources.getColor(R.color.colorWhite))
            }
            else {
                btn_disease_7.isSelected = true
                btn_disease_1.isSelected = false
                btn_disease_2.isSelected = false
                btn_disease_3.isSelected = false
                btn_disease_4.isSelected = false
                btn_disease_5.isSelected = false
                btn_disease_6.isSelected = false

                btn_disease_1.setTextColor(resources.getColor(R.color.colorWhite))
                btn_disease_2.setTextColor(resources.getColor(R.color.colorWhite))
                btn_disease_3.setTextColor(resources.getColor(R.color.colorWhite))
                btn_disease_4.setTextColor(resources.getColor(R.color.colorWhite))
                btn_disease_5.setTextColor(resources.getColor(R.color.colorWhite))
                btn_disease_6.setTextColor(resources.getColor(R.color.colorWhite))
                btn_disease_7.setTextColor(resources.getColor(R.color.colorPrimary))
            }
            checkSelectButton()
        }
    }

    private fun checkSelectButton(){
        if(btn_disease_1.isSelected || btn_disease_2.isSelected || btn_disease_3.isSelected || btn_disease_4.isSelected ||btn_disease_5.isSelected || btn_disease_6.isSelected || btn_disease_7.isSelected ){
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
