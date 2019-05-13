package com.wagnermessias.olodjinha.core.di

import com.wagnermessias.olodjinha.core.data.BannerRepository
import com.wagnermessias.olodjinha.core.data.CategoryRepository
import com.wagnermessias.olodjinha.core.data.RetrofitService
import com.wagnermessias.olodjinha.core.data.remote.ALodjinhaApi
import com.wagnermessias.olodjinha.core.data.ProductRepository
import com.wagnermessias.olodjinha.core.interactor.LoadBannerInteractor
import com.wagnermessias.olodjinha.core.interactor.LoadCategoryInteractor
import com.wagnermessias.olodjinha.core.interactor.LoadBestSellersInteractor
import com.wagnermessias.olodjinha.core.interactor.LoadProductByCategoryInteractor
import com.wagnermessias.olodjinha.core.interactor.ReservationProductInteractor
import com.wagnermessias.olodjinha.feature.home.HomeViewModel
import com.wagnermessias.olodjinha.feature.products.bycategory.ProductsByCategoryViewModel
import com.wagnermessias.olodjinha.feature.products.detail.ProductDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel

import org.koin.dsl.module

val homeModule = module {

    single { RetrofitService.retrofit().create<ALodjinhaApi>(ALodjinhaApi::class.java) }
    factory { BannerRepository(get()) }
    factory { CategoryRepository(get()) }
    factory { ProductRepository(get()) }
    factory { LoadBannerInteractor(get()) }
    factory { LoadCategoryInteractor(get()) }
    factory { LoadBestSellersInteractor(get()) }
    viewModel { HomeViewModel(get(), get(), get()) }
}

val productsModule = module {
    factory { LoadProductByCategoryInteractor(get()) }
    viewModel { ProductsByCategoryViewModel(get()) }
}

val productsDetailModule = module {
    factory { ReservationProductInteractor(get()) }
    viewModel { ProductDetailViewModel(get()) }
}
