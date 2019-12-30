package com.example.caredirection.research.userInfo

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableStringBuilder
import com.example.caredirection.R
import android.text.Spannable
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.WindowManager
import android.widget.*
import com.example.caredirection.common.logDebug
import com.example.caredirection.common.toast
import com.example.caredirection.research.DB.ResearchKeeper
import kotlinx.android.synthetic.main.activity_research_gender.*
import kotlinx.android.synthetic.main.dialog_yearpicker.*
import java.util.*


class ResearchGenderActivity : AppCompatActivity() {

    private lateinit var keeper :ResearchKeeper

    private var gender_selec: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_research_gender)
        keeper = ResearchKeeper(this)

        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        cl_gender.setPadding(0, statusBarHeight(this), 0, 0)

        keeper.gender?.let {
            when (it) {
                ResearchKeeper.MALE -> {
                    chtxt_gender_women.isChecked = false
                    chtxt_gender_man.isChecked = true
                    gender_selec = true
                }
                else -> {
                    chtxt_gender_women.isChecked = true
                    chtxt_gender_man.isChecked = false
                    gender_selec = true
                }
            }
        }

        keeper.year?.let {
            //TODO: 이거 피커로 변경해서 피커를 세팅
            txt_year_picker.text = it.toString()
        }

        checkSelectButton()
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

        txt_gender_nametitle?.text = name + "님,"
        txt_gender_namesubtitle?.text = name + "님만의"

        btn_gender_next?.setOnClickListener{
            val year = txt_year_picker?.text

            if(!gender_selec || year?.isEmpty()!!){
                toast("아직 체크하지 않은 사항이 있습니다.")
            }
            else {
                keeper.gender = if (chtxt_gender_women.isChecked) ResearchKeeper.FEMALE else ResearchKeeper.MALE
                //TODO: 이거 피커 값으로 세팅해주어야 함.
                keeper.year = 1999

                val disease_intent = Intent(this, ResearchDiseaseActivity::class.java)
                disease_intent.putExtra("username", name)

                startActivity(disease_intent)
            }
        }

        chtxt_gender_women?.setOnClickListener{
            chtxt_gender_women?.isChecked = true
            chtxt_gender_man?.isChecked = false
            gender_selec = true
            checkSelectButton()
        }

        chtxt_gender_man?.setOnClickListener{
            chtxt_gender_women?.isChecked = false
            chtxt_gender_man?.isChecked = true
            gender_selec = true
            checkSelectButton()
        }

        txt_year_picker?.setOnClickListener{
            val builder = AlertDialog.Builder(this)
            val dialogView = layoutInflater.inflate(R.layout.dialog_yearpicker, null)

            //number_picker.formatter = String.format()
//            number_picker.setOnValueChangedListener(object : NumberPicker.OnValueChangeListener() {
//                override fun onValueChange(picker: NumberPicker, oldVal: Int, newVal: Int) {
//                    String.format(Locale.US, "oldVal: %d, newVal: %d", oldVal, newVal).logDebug()
//                }
//            })

            builder.setView(dialogView)
                .setPositiveButton("확인") { dialogInterface, i ->
                    //txt_year_picker?.text = number_picker.value.toString()
                    /* 확인일 때 main의 View의 값에 dialog View에 있는 값을 적용 */
                }
                .setNegativeButton("취소") { dialogInterface, i ->
                    txt_year_picker?.text = ""
                }
                .show()

            checkSelectButton()
        }
    }

    private fun checkSelectButton(): Boolean{
        if(gender_selec || txt_year_picker.text.isNotEmpty()){
            return true
        }
        return false
    }

    // 강조타이틀 설정
    private fun setColorInPartitial(){
        val builder = SpannableStringBuilder(txt_gender_subtitle?.text.toString())

        builder.setSpan(
            ForegroundColorSpan(Color.parseColor("#ebf0b0")),
            0,
            9,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        txt_gender_subtitle?.text = builder
    }
}
