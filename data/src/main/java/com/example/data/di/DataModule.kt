package com.example.data.di

import com.example.data.repositories.banner.BannerListRepository
import com.example.data.repositories.bestSeller.BestSellerRepository
import com.example.data.repositories.category.CategoryRepository
import com.example.data.repositories.product.ListProductRepository
import com.example.data.repositories.product.ProductRepository
import com.example.data.repositories.product.ReserveProductRepository
import com.example.domain.repositories.banner.IBannerListRepository
import com.example.domain.repositories.bestSeller.IBestSellerListRepository
import com.example.domain.repositories.category.ICategoryListRepository
import com.example.domain.repositories.product.IProductListRepository
import com.example.domain.repositories.product.IProductRepository
import com.example.domain.repositories.product.IReserveProductRepository
import org.koin.dsl.module

val dataModule = module {
    single<IBannerListRepository> {
        BannerListRepository(api = get())
    }

    single<ICategoryListRepository> {
        CategoryRepository(api = get())
    }

    single<IBestSellerListRepository> {
        BestSellerRepository(api = get())
    }

    single<IProductRepository> {
        ProductRepository(api = get())
    }

    single<IReserveProductRepository> {
        ReserveProductRepository(api = get())
    }

    single<IProductListRepository> {
        ListProductRepository(api = get())
    }
}