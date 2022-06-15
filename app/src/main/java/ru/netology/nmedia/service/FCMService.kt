package ru.netology.nmedia.service

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.google.gson.Gson

class FCMService : FirebaseMessagingService() {

    private val gson = Gson()
    override fun onMessageReceived(message: RemoteMessage) {
       Log.d("onMessageReceived", gson.toJson(message))
    }

    override fun onNewToken(token: String) {
        Log.d("onNewToken", token)
    }
}

//dyWrZ7A-RdWnFnx4-QhQTY:APA91bGmr7B27B-hef2vXEfEDb4WH1tpl2vPi_KP2l357sBXV-S0-krI6nNROdDaJpD-GdLxKy47qZWNU1qqoKilKQhMr7FihLhja3r5Fc37rxf7TOYWA-I14uSKKbMeRyzEL-hkbW3l