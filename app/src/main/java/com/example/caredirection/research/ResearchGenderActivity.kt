package com.example.caredirection.research

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.widget.Button
import android.widget.TextView
import com.example.caredirection.R
import com.example.caredirection.common.toast
import android.text.Spannable
import android.text.style.ForegroundColorSpan
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T



class ResearchGenderActivity : AppCompatActivity() {

    private var txt_gender_subtitle: TextView? = null
    private var btn_gender_next: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_research_gender)

        makeController()
        setColorInPartitial()
    }

    // 사용자 입력받아서 초기화
    private fun makeController(){
        //edt_username = findViewById((R.id.edt_username))
        btn_gender_next = findViewById(R.id.btn_gender_next)
        txt_gender_subtitle = findViewById(R.id.txt_gender_subtitle1)

        btn_gender_next?.setOnClickListener{
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
//                val intent = Intent(this,ResearchGenderActivity::class.java)
//                intent.putExtra("name",name)
//
//                startActivity(intent)
//            }
        }
    }

    private fun setColorInPartitial(){
        val builder = SpannableStringBuilder(txt_gender_subtitle?.text)
        builder.setSpan(
            ForegroundColorSpan(Color.parseColor("#ebf0b0")),
            0,
            9,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        txt_gender_subtitle?.text = builder.toString()
        }
}
