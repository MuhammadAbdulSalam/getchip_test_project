package com.salam.getchip.application

import android.app.Application
import kotlin.system.exitProcess

class Application : Application() {
    override fun onCreate() {
        super.onCreate()
        Thread.setDefaultUncaughtExceptionHandler { _, _ -> //Catch your exception
            exitProcess(2)
        }
    }
}