package com.example.caredirection.common.fcm

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class CadiFirebaseMessagingService : FirebaseMessagingService(){

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        //TODO: Send Server to Refresh Token
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        //TODO: Notification Controller -> Make Notification
//        val notifiation = notificationController.make(remoteMessage.data)
//        pushControler.push(notifiation)
        //TODO: Push Controller -> Push Notification
    }
}