package com.app.ecom

import android.app.Application
import com.app.ecom.utils.RetroEaseLocalPreferences
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class BaseApplication: Application() {

    lateinit var localSharedPrefStorage: RetroEaseLocalPreferences
    override fun onCreate() {
        super.onCreate()
        instance = this
        localSharedPrefStorage = RetroEaseLocalPreferences(this)
    }

    fun getLocalPreferneces(): RetroEaseLocalPreferences? {
        return localSharedPrefStorage
    }

    companion object {
        @get:Synchronized
        lateinit var instance: BaseApplication
            private set
    }
}