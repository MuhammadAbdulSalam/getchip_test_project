package com.salam.getchip.application

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import kotlin.system.exitProcess

/**
 * @author Muhammad Abdul Salam
 */

@HiltAndroidApp
class Application : Application() {
    override fun onCreate() {
        super.onCreate()
        Thread.setDefaultUncaughtExceptionHandler { _, _ ->
            exitProcess(2)
        }
    }
}
