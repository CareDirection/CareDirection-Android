package com.example.caredirection

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.caredirection.common.fcm.getFirebaseToken
import com.example.caredirection.common.logDebug
import com.example.caredirection.common.toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toast("안녕 친구들")

        "seung".logDebug()

        getFirebaseToken { token ->
            "firebase token : $token".logDebug()
        }
    }
}
