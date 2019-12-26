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
import android.widget.Button
import android.widget.TextView
import com.example.caredirection.R
import com.example.caredirection.research.ResearchChange
import kotlinx.android.synthetic.main.activity_research_symptom.*

class ResearchSymptomActivity : AppCompatActivity() {

    private var btn_symptom_next: Button? = null
    private var txt_symptom_subtitle: TextView? = null
    private var txt_symptom_nametitle: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_research_symptom)

        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        cl_symptom.setPadding(0, statusBarHeight(this), 0, 0)

        makeController()
        setColorInPartitial()
    }

    fun statusBarHeight(context: Context): Int {
        val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")

        return if (resourceId > 0) context.resources.getDimensionPixelSize(resourceId)
        else 0
    }

    // 사용자 입력받아서 초기화
    private fun makeController(){
        //edt_username = findViewById((R.id.edt_username))
        btn_symptom_next = findViewById(R.id.btn_disease_next)
        txt_symptom_subtitle = findViewById(R.id.txt_symptom_subtitle)
        txt_symptom_nametitle = findViewById(R.id.txt_symptom_nametitle)

        val name: String = intent.getStringExtra("username")

        txt_symptom_nametitle?.text = name + "님께서는"

//        edt_username?.addTextChangedListener(object : TextWatcher{
//            override fun afterTextChanged(p0: Editable?) {
//                toast("입력")
//            }
//        })

        btn_symptom_next?.setOnClickListener{
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
                val intent = Intent(this,ResearchChange::class.java)
                intent.putExtra("username",name)

                startActivity(intent)
//            }
        }
    }

    // 강조타이틀 설정
    private fun setColorInPartitial(){
        val builder = SpannableStringBuilder(txt_symptom_subtitle?.text.toString())

        builder.setSpan(
            ForegroundColorSpan(Color.parseColor("#ebf0b0")),
            0,
            5,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        txt_symptom_subtitle?.text = builder
    }
}
