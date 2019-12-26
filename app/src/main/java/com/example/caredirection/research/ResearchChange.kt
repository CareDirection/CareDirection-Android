package com.example.caredirection.research

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.WindowManager
import android.widget.TextView
import com.example.caredirection.R
import kotlinx.android.synthetic.main.activity_research_change.*

class ResearchChange : AppCompatActivity() {

    private var txt_change_title: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_research_change)

        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        cl_change.setPadding(0, statusBarHeight(this), 0, 0)

        txt_change_title = findViewById(R.id.txt_change_title)

        setColorInPartitial()
    }

    fun statusBarHeight(context: Context): Int {
        val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")

        return if (resourceId > 0) context.resources.getDimensionPixelSize(resourceId)
        else 0
    }

    // 강조타이틀 설정
    private fun setColorInPartitial(){
        val builder = SpannableStringBuilder(txt_change_title?.text.toString())

        builder.setSpan(
            ForegroundColorSpan(Color.parseColor("#ebf0b0")),
            0,
            11,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        txt_change_title?.text = builder
    }
}
