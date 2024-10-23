package com.example.apiconnectionshowcase

import android.app.Application
import android.util.Log
import com.example.apiconnectionshowcase.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.workmanager.koin.workManagerFactory
import org.koin.core.context.startKoin

class APIConnectionShowcase: Application() {
    override fun onCreate() {
        super.onCreate()
//        startKoin {
//            androidContext(applicationContext)
////            workManagerFactory()
//            modules(appModules)
//        }
        try {
            startKoin {
                androidContext(this@APIConnectionShowcase)
                workManagerFactory()
                modules(appModules)
            }
        } catch (e: Exception) {
            Log.e("KoinSetup", "Koin setup failed: ${e.message}")
        }
    }
}