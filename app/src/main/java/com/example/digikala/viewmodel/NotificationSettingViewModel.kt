package com.example.digikala.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.work.WorkerParameters
import com.example.digikala.utillity.workmanager.NewProductNotifyWorker

class NotificationSettingViewModel(application: Application) : AndroidViewModel(application) {

    fun scheduleNotification(min : Long){
        NewProductNotifyWorker.scheduleNotification(getApplication(),min,true)
    }
}