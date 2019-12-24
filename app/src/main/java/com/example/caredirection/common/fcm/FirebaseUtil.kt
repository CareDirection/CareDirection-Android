package com.example.caredirection.common.fcm

import com.example.caredirection.common.logWarn
import com.google.firebase.iid.FirebaseInstanceId

fun getFirebaseToken(tokenMaker: (String) -> Unit) {
    FirebaseInstanceId.getInstance().instanceId
        .addOnCompleteListener { task ->
            if (!task.isSuccessful){
                "getInstanceId failed ${task.exception}".logWarn()
                return@addOnCompleteListener
            }

            tokenMaker(task.result?.token.orEmpty())
        }
}