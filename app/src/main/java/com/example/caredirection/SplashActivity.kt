package com.example.caredirection

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.example.caredirection.login.ActivityLogin
import com.example.caredirection.research.ResearchActivity
import com.example.caredirection.research.userInfo.ResearchNameActivity
import kotlinx.android.synthetic.main.activity_splash.*
import java.lang.Exception
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            Thread.sleep(2000)
            val intent = Intent(this, ActivityLogin::class.java)
            //스플래시 화면에서 로그인 화면으로 넘어가는 걸로 구현

            startActivity(intent)

            finish()
        } catch (e: Exception) {
        }
    }
}
