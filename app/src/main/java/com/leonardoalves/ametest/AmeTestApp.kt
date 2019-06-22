package com.leonardoalves.ametest

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AmeTestApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startDependencies()
    }

    fun startDependencies(){
        startKoin {
            androidContext(this@AmeTestApp)
        }
        Modules.init()
    }
}