package com.leonardoalves.ametest

import com.leonardoalves.ametest.store.StoreFragment
import com.leonardoalves.ametest.store.StorePresenter
import com.leonardoalves.repository.remoteDataSourceModule
import com.leonardoalves.repository.repositoryModule
import org.koin.core.context.loadKoinModules
import org.koin.core.qualifier.named
import org.koin.dsl.module

object Modules {
    fun init() = loadKoinModules(listOf(
        remoteDataSourceModule, repositoryModule, presentationModule
    ))
}

val presentationModule = module {
    scope(named<StoreFragment>()) {
        scoped { StorePresenter(get()) }
    }
}