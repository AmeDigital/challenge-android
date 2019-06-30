package com.example.domain.di

import com.example.domain.usecases.banner.BannerList
import com.example.domain.usecases.banner.BannerListUseCase
import com.example.domain.usecases.bestSeller.BestSellerList
import com.example.domain.usecases.bestSeller.BestSellerListUseCase
import com.example.domain.usecases.category.CategoryList
import com.example.domain.usecases.category.CategoryListUseCase
import com.example.domain.usecases.product.*
import org.koin.dsl.module

object DomainModule {
    val domainModule = module {
        single<BannerList> {
            BannerListUseCase(repository = get())
        }

        single<CategoryList> {
            CategoryListUseCase(repository = get())
        }

        single<BestSellerList> {
            BestSellerListUseCase(repository = get())
        }

        single<Product> {
            ProductUseCase(repository = get())
        }

        single<ReserveUseCase> {
            ReserveProductUseCase(repository = get())
        }

        single<ProductList> {
            ProductListUseCase(repository = get())
        }
    }
}