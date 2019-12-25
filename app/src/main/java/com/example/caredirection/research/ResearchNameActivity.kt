package com.example.caredirection.research

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import com.example.caredirection.R
import com.example.caredirection.common.toast

class ResearchNameActivity : AppCompatActivity() {

    private var edt_username: EditText? = null
    private var btn_name_next: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_research_name)

        //TODO: ResearchController 를 확인해서, 어느 지점까지 유요한 정보가 들어있는지 판단하고
        //TODO: 유효하지 않은 정보가 들어있는 화면까지 Intent list 를 만들어서
        //TODO: startActivities 를 호출해주면 됩니다.

        makeController()
    }

    // 사용자 입력받아서 초기화
    private fun makeController(){
        edt_username = findViewById((R.id.edt_username))
        btn_name_next = findViewById(R.id.btn_gender_next)

//        edt_username?.addTextChangedListener(object : TextWatcher{
//            override fun afterTextChanged(p0: Editable?) {
//                toast("입력")
//            }
//        })

        btn_name_next?.setOnClickListener{
            val name = edt_username?.text.toString()

            // 이름 빈칸일 경우,
            if(name.isEmpty()){
                toast("입력")
            }
            else{
                //btn_name_next.
                //btn_name_next.setBackgroundResource(R.drawable.yellow_border)

                val intent = Intent(this,ResearchGenderActivity::class.java)
                intent.putExtra("name",name)

                startActivity(intent)
            }
        }
    }
}
