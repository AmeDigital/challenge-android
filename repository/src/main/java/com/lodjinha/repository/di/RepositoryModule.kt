package com.lodjinha.repository.di

import com.lodjinha.repository.AppDispatchers
import com.lodjinha.repository.LodjinhaRepository
import com.lodjinha.repository.LodjinhaRepositoryImpl
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module.module

val repositoryModule = module {
    factory {
        AppDispatchers(Dispatchers.Main, Dispatchers.IO)
    }

    factory {
        LodjinhaRepositoryImpl(get()) as LodjinhaRepository
    }
}
