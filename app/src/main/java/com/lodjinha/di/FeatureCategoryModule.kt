package com.lodjinha.di

import com.lodjinha.category.CategoryViewModel
import com.lodjinha.category.GetProductsUseCase
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val featureCategoryModule = module {
    factory { GetProductsUseCase(get()) }
    viewModel { CategoryViewModel(get(), get()) }
}
