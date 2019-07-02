package com.lodjinha.di

import com.lodjinha.home.GetBannerUseCase
import com.lodjinha.home.CategoriesUseCase
import com.lodjinha.home.HomeViewModel
import com.lodjinha.home.TopSellingUseCase
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val featureHomeModule = module {
    factory { GetBannerUseCase(get()) }
    factory { CategoriesUseCase(get()) }
    factory { TopSellingUseCase(get()) }
    viewModel { HomeViewModel(get(), get(), get(), get()) }
}
