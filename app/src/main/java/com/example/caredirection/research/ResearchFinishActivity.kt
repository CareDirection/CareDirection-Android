package com.example.caredirection.research

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.example.caredirection.R
import com.example.caredirection.care_product.CareProductActivity
import com.example.caredirection.home.HomeActivity
import com.example.caredirection.research.DB.ResearchKeeper
import kotlinx.android.synthetic.main.activity_research_finish.*

class ResearchFinishActivity : AppCompatActivity() {

    private lateinit var keeper : ResearchKeeper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_research_finish)

        keeper = ResearchKeeper(this)

        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        cl_finish.setPadding(0, statusBarHeight(this), 0, 0)

        btn_finish_next.setOnClickListener{

            keeper.researchfinish = 1
//            val intent = Intent(this,LifeStyleActivity::class.java)
//            intent.putExtra("username",name)

            startActivity(Intent(this, CareProductActivity::class.java))
        }
    }

    // 상태바 배경투명 설정
    fun statusBarHeight(context: Context): Int {
        val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")

        return if (resourceId > 0) context.resources.getDimensionPixelSize(resourceId)
        else 0
    }
}
