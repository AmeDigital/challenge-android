package com.lodjinha.home.di

import com.lodjinha.home.BannerUseCase
import com.lodjinha.home.HomeViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val featureLoginModule = module {
    factory { BannerUseCase(get()) }
    viewModel { HomeViewModel(get(), get()) }
}
