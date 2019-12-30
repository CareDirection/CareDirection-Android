package com.example.caredirection.research.lifestyle

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import com.example.caredirection.R
import kotlinx.android.synthetic.main.activity_life_style.*

class LifeStyleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_life_style)
        configureLifeStyleTab()

        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        cl_life.setPadding(0, statusBarHeight(this), 0, 0)

        val name = intent.getStringExtra("username")
    }

    // 상태바 배경투명 설정
    fun statusBarHeight(context: Context): Int {
        val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")

        return if (resourceId > 0) context.resources.getDimensionPixelSize(resourceId)
        else 0
    }

    private fun configureLifeStyleTab(){
        vp_life_style.adapter = LifeStylePagerAdapter(supportFragmentManager,4)
        vp_life_style.offscreenPageLimit = 3
        tl_life_style.setupWithViewPager(vp_life_style)

        val navIndicatorMainLayout : View = (this.getSystemService(android.content.Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(R.layout.tab_dot_indicator,null,false)
        tl_life_style.getTabAt(0)!!.customView = navIndicatorMainLayout.findViewById(R.id.img_dot_1) as ImageView
        tl_life_style.getTabAt(1)!!.customView = navIndicatorMainLayout.findViewById(R.id.img_dot_2) as ImageView
        tl_life_style.getTabAt(2)!!.customView = navIndicatorMainLayout.findViewById(R.id.img_dot_3) as ImageView
        //tl_life_style.getTabAt(3)!!.customView = navIndicatorMainLayout.findViewById(R.id.img_dot_alcohol) as ImageView
    }
}
