package com.example.caredirection.care_product

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.caredirection.R
import com.example.caredirection.home.HomeActivity
import com.example.caredirection.research.DB.ResearchKeeper
import kotlinx.android.synthetic.main.activity_care_register_complete.*

class CareRegisterComplete : AppCompatActivity() {

    private lateinit var keeper : ResearchKeeper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_care_register_complete)

        keeper = ResearchKeeper(this)

        txt_complete_next.setOnClickListener {
            keeper.careProductComplete = 1

            startActivity(Intent(this, HomeActivity::class.java))
        }
    }
}
