package com.example.lodjinha

import android.app.Application
import com.example.data.di.dataModule
import com.example.domain.di.DomainModule.domainModule
import com.example.lodjinha.di.ApplicationModule.appModule
import com.example.lodjinha.di.PresenterModule.presenterModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class LodjinhaApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@LodjinhaApplication)
            modules(presenterModule, domainModule, dataModule, appModule)
        }
    }
}