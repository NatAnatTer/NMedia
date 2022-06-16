package ru.netology.nmedia.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.google.gson.Gson
import ru.netology.nmedia.R
import kotlin.random.Random

class FCMService : FirebaseMessagingService() {

    private val gson = Gson()

    override fun onCreate() {
        super.onCreate()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.channel_remote_name)
            val descriptionText = getString(R.string.channel_remote_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }


    override fun onMessageReceived(message: RemoteMessage) {
        // Log.d("onMessageReceived", gson.toJson(message.data))
        val data = message.data
        val serializedAction = data[Action.KEY] ?: return
        val action = Action.values().find { it.key == serializedAction } ?: return

        when (action) {
            Action.Like -> handleLikeAction(data[CONTENT_KEY] ?: return)

        }
    }

    override fun onNewToken(token: String) {
        Log.d("onNewToken", token)
    }

    private fun handleLikeAction(serializedContent: String) {
        val likeContent = gson.fromJson(serializedContent, Like::class.java)

        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle(
                getString(
                    R.string.notification_user_liked,
                    likeContent.userName,
                    likeContent.postAuthor
                )
            )
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()
        NotificationManagerCompat.from(this)
            .notify(Random.nextInt(100_000), notification)
    }

    private companion object {
        const val CONTENT_KEY = "content"
        const val CHANNEL_ID = "remote"
    }
}

//dyWrZ7A-RdWnFnx4-QhQTY:APA91bGmr7B27B-hef2vXEfEDb4WH1tpl2vPi_KP2l357sBXV-S0-krI6nNROdDaJpD-GdLxKy47qZWNU1qqoKilKQhMr7FihLhja3r5Fc37rxf7TOYWA-I14uSKKbMeRyzEL-hkbW3l