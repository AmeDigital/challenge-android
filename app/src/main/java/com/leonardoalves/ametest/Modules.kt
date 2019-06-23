package com.leonardoalves.ametest

import com.leonardoalves.ametest.store.StorePresenter
import com.leonardoalves.ametest.store.StoreView
import com.leonardoalves.ametest.store.category.CategoryPresenter
import com.leonardoalves.ametest.store.category.CategoryView
import com.leonardoalves.ametest.store.product.ProductPresenter
import com.leonardoalves.ametest.store.product.ProductView
import com.leonardoalves.repository.remoteDataSourceModule
import com.leonardoalves.repository.repositoryModule
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

object Modules {
    fun init() = loadKoinModules(listOf(
        remoteDataSourceModule, repositoryModule, presentationModule
    ))
}

val presentationModule = module {
    factory { (view: StoreView) -> StorePresenter(get(), view) }
    factory { (view: CategoryView) -> CategoryPresenter(get(), view) }
    factory { (view: ProductView) -> ProductPresenter(get(), view) }
}