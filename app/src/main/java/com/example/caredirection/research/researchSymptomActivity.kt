package com.example.caredirection.research

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.caredirection.R
import com.example.caredirection.common.toast

class researchSymptomActivity : AppCompatActivity() {

    private var btn_symptom_next: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_research_symptom)

        makeController()
    }

    // 사용자 입력받아서 초기화
    private fun makeController(){
        //edt_username = findViewById((R.id.edt_username))
        btn_symptom_next = findViewById(R.id.btn_disease_next)

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
//                val intent = Intent(this,ResearchGenderActivity::class.java)
//                intent.putExtra("name",name)
//
//                startActivity(intent)
//            }
        }
    }
}
