package com.example.caredirection.research.userInfo

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.View
import android.view.WindowManager
import android.widget.Button
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
            btn_disease_1, btn_disease_2, btn_disease_3, btn_disease_4, btn_disease_5, btn_disease_6
        )

        keeper.disease?.let { set ->
            disButtons
                .filter { it.text in set }
                .forEach {
                    it.isChecked = true
                    it.setTextColor(resources.getColor(R.color.colorPrimary))
                    checkSelectButton()
                }
            if(btn_disease_clear.text in set){
                btn_disease_clear.isChecked = true
                btn_disease_clear.setTextColor(resources.getColor(R.color.colorPrimary))
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
            if(btn_disease_clear.isChecked){
                checkBtnColor(btn_disease_clear,true)
                disButtons.forEach {
                    checkBtnColor(it,false)
                }
            }
            else {
                checkBtnColor(btn_disease_clear,false)
            }
            checkSelectButton()
        }

        disButtons.forEachIndexed { index, checkBox ->
            disButtons[index].setOnClickListener{
                if(disButtons[index].isChecked){
                    checkBtnColor(disButtons[index],true)
                    checkBtnColor(btn_disease_clear,false)
                }
                else{
                    checkBtnColor(disButtons[index],false)
                }
                checkSelectButton()
            }
        }

        btn_disease_next.setOnClickListener{

            val set = mutableSetOf<String>()
            disButtons
                .filter { it.isChecked }
                .forEach { set.add(it.text.toString()) }
            if(btn_disease_clear.isChecked) set.add(btn_disease_clear.text.toString())
            keeper.disease = set

            toast(keeper.disease.toString())

            val symptom_intent = Intent(this,ResearchSymptomActivity::class.java)
            startActivity(symptom_intent)
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
        if(disButtons.any { it.isChecked }||btn_disease_clear.isChecked){
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
