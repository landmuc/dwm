package com.landmuc.dwm

import android.app.Application
import com.landmuc.dwm.di.sharedModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            modules(sharedModule)
        }
    }
}