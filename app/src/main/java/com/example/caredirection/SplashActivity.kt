package com.example.caredirection

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.example.caredirection.research.userInfo.ResearchNameActivity
import kotlinx.android.synthetic.main.activity_splash.*
import java.lang.Exception

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        cl_splash.setPadding(0, statusBarHeight(this), 0, 0)

        startLoading()
    }

    // 상태바 배경투명 설정
    fun statusBarHeight(context: Context): Int {
        val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")

        return if (resourceId > 0) context.resources.getDimensionPixelSize(resourceId)
        else 0
    }

    private fun startLoading() {
        try {
            Thread.sleep(2000)
            val intent = Intent(this, ResearchNameActivity::class.java)
            //스플래시 화면에서 로그인 화면으로 넘어가는 걸로 구현

            startActivity(intent)

            finish()
        } catch (e: Exception) {
        }
    }
}
