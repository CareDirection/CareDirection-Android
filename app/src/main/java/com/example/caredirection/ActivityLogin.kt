package com.example.caredirection

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*

class ActivityLogin : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btn_activity_register.setOnClickListener{
            startActivity(Intent(this@ActivityLogin, ActivityRegister::class.java))

        }
    }
}
