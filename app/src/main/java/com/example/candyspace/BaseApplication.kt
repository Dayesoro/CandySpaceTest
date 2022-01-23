package com.example.candyspace

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/** App initialization point **/
@HiltAndroidApp
class BaseApplication:Application() {
    override fun onCreate() {
        Timber.plant(Timber.DebugTree())
        super.onCreate()
    }
}