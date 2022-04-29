package com.amedigital.challenge_android

import com.amedigital.challenge_home.HomeViewModel
import org.koin.dsl.module
import com.amedigital.challenge_model.api.NetworkUtils
import com.amedigital.challenge_model.repositories.BannerRepository
import com.amedigital.challenge_model.repositories.BannerRepositoryImpl
import org.koin.androidx.viewmodel.dsl.viewModel

val appModules = module {
    single<BannerRepository> { BannerRepositoryImpl(get()) }
    single { NetworkUtils.createApiService() }
    viewModel { HomeViewModel(get()) }
}