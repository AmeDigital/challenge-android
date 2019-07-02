package com.lodjinha.di

import com.lodjinha.detailProduct.DetailProductViewModel
import com.lodjinha.detailProduct.ReserveProductUseCase
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val featureDetailProductModule = module {
    factory { ReserveProductUseCase(get()) }
    viewModel { DetailProductViewModel(get(), get()) }
}
