package com.dadabazar

import android.app.Application
import com.dadabazar.data.DataManager

class App : Application() {
    var dataManager: DataManager? = null
    override fun onCreate() {
        super.onCreate()
        dataManager = DataManager()
    }
}