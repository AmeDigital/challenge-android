package com.lodjinha

import android.app.Application
import com.lodjinha.di.appComponent
import org.koin.android.ext.android.startKoin

open class App: Application() {

    override fun onCreate() {
        super.onCreate()
        configureDi()
    }

    open fun configureDi() =
        startKoin(this, provideComponent())

    open fun provideComponent() = appComponent
}
