package com.example.caredirection.research

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.caredirection.R

class ResearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enquete)

        //TODO: ResearchController 를 확인해서, 어느 지점까지 유요한 정보가 들어있는지 판단하고
        //TODO: 유효하지 않은 정보가 들어있는 화면까지 Intent list 를 만들어서
        //TODO: startActivities 를 호출해주면 됩니다.
    }
}
