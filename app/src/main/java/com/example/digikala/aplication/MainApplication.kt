package com.example.digikala.aplication

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager

class MainApplication  : Application() {
    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }
    private fun createNotificationChannel(){
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O){
            val id = "digikala_channel_id"
            val name = "DigiKala Channel"
            val importance = NotificationManager.IMPORTANCE_DEFAULT

            var notificationChannel = NotificationChannel(id,name,importance)
            var notifManager : NotificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notifManager.createNotificationChannel(notificationChannel)
        }
    }
}