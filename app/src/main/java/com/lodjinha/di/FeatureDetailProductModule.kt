package com.lodjinha.di

import com.lodjinha.detailProduct.DetailProductViewModel
import com.lodjinha.detailProduct.ProductUseCase
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val featureDetailProductModule = module {
    factory { ProductUseCase(get()) }
    viewModel { DetailProductViewModel(get(), get()) }
}
