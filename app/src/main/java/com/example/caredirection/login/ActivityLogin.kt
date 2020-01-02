package com.example.caredirection.login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.example.caredirection.R
import com.example.caredirection.research.ResearchActivity
import kotlinx.android.synthetic.main.activity_login.*

class ActivityLogin : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        cl_login.setPadding(0, statusBarHeight(this), 0, 0)

        btn_activity_login.setOnClickListener {
            startActivity(Intent(this@ActivityLogin, ResearchActivity::class.java))
        }

        btn_activity_register.setOnClickListener{
            startActivity(Intent(this@ActivityLogin, ActivityRegister::class.java))

        }
    }

    // 상태바 배경투명 설정
    fun statusBarHeight(context: Context): Int {
        val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")

        return if (resourceId > 0) context.resources.getDimensionPixelSize(resourceId)
        else 0
    }
}
