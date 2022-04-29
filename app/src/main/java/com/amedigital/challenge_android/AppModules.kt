package com.amedigital.challenge_android

import com.amedigital.challenge_home.HomeViewModel
import org.koin.dsl.module
import com.amedigital.challenge_model.api.NetworkUtils
import com.amedigital.challenge_model.repositories.BannerRepository
import com.amedigital.challenge_model.repositories.BannerRepositoryImpl
import com.amedigital.challenge_model.repositories.CategoriaRepository
import com.amedigital.challenge_model.repositories.CategoriaRepositoryImpl
import com.amedigital.challenge_model.repositories.ProdutoRepository
import com.amedigital.challenge_model.repositories.ProdutoRepositoryImpl
import com.amedigital.challenge_produto.ProdutoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel

val appModules = module {
    single<BannerRepository> { BannerRepositoryImpl(get()) }
    single<CategoriaRepository> { CategoriaRepositoryImpl(get()) }
    single<ProdutoRepository> { ProdutoRepositoryImpl(get()) }
    single { NetworkUtils.createApiService() }
    viewModel { HomeViewModel(get(), get(), get()) }
    viewModel { params -> ProdutoViewModel(params[0], get()) }
}