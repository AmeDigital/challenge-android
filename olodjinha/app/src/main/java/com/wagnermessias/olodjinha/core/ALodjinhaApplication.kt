package com.wagnermessias.olodjinha.core

import android.app.Application
import com.wagnermessias.olodjinha.core.di.homeModule
import com.wagnermessias.olodjinha.core.di.productsDetailModule
import com.wagnermessias.olodjinha.core.di.productsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class ALodjinhaApplication : Application() {

        override fun onCreate() {
            super.onCreate()
            // Start Koin
            startKoin{
                androidLogger()
                androidContext(this@ALodjinhaApplication)
                modules(homeModule, productsModule,productsDetailModule)
            }
        }

}