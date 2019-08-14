package com.luizzabuscka.alodjinha.application

import android.app.Application
import com.luizzabuscka.viewmodel.CategoryViewModel
import com.luizzabuscka.viewmodel.HomeViewModel
import com.luizzabuscka.viewmodel.ProductViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class LodjinhaApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidLogger()
            androidContext(this@LodjinhaApplication)
            modules(appModule)
        }
    }

}

val appModule = module {
    viewModel { HomeViewModel() }
    viewModel { CategoryViewModel(getProperty("id")) }
    viewModel { ProductViewModel() }
}