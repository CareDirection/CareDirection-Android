package com.example.caredirection.research

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.WindowManager
import android.widget.TextView
import com.example.caredirection.R
import com.example.caredirection.common.logDebug
import com.example.caredirection.common.toast
import com.example.caredirection.data.network.InfoData
import com.example.caredirection.network.RequestURL
import com.example.caredirection.research.DB.ResearchKeeper
import com.example.caredirection.research.lifestyle.LifeStyleActivity
import kotlinx.android.synthetic.main.activity_research_change.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ResearchChangeActivity : AppCompatActivity() {

    private var txt_change_title: TextView? = null
    private lateinit var keeper : ResearchKeeper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_research_change)

        keeper = ResearchKeeper(this)

        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        cl_change.setPadding(0, statusBarHeight(this), 0, 0)


        txt_change_title = findViewById(R.id.txt_change_title)

        btn_change_next.setOnClickListener{
            keeper.lifeCycle = 1

            val name = keeper.name!!
            val gender = keeper.gender!!
            val year = keeper.year!!

            postInfoResponse(name,gender,year)

            startActivity(Intent(this, LifeStyleActivity::class.java))
        }

        setColorInPartitial()
    }
    private fun postInfoResponse(name: String,gender: Int,birth: String){
        val call: Call<InfoData> = RequestURL.service.postInfo("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkeCI6NjEsImlhdCI6MTU3ODAwOTc4NiwiZXhwIjo4Nzk3ODAwOTc4NiwiaXNzIjoiY2FyZS1kaXJlY3Rpb24ifQ.vCa_1zQwS1x5HWYItuj55hdk5_k4U8WwwhJrBjkJZ08",name,gender,birth)
        call.enqueue(
            object : Callback<InfoData> {
                override fun onFailure(call: Call<InfoData>, t: Throwable) {
                    t.toString().logDebug()
                }
                override fun onResponse(
                    call: Call<InfoData>,
                    response: Response<InfoData>
                ) {
                    Log.d("haeeul", "${response.message()}")
                    if (response.isSuccessful) {
                        val InfoRepos : InfoData = response.body()!!
                        val message = InfoRepos.message
                        Log.d("haeeul", "성공 ${response.body()}")
                        toast(message)
                    } else {
                        Log.d("haeeul","실패 ${response.errorBody()?.string()}")
                        toast("실패")
                    }
                }
            }
        )
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
            5,
            11,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        txt_change_title?.text = builder
    }
}
