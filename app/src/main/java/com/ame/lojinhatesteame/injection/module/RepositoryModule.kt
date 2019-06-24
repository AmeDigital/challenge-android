package com.ame.lojinhatesteame.injection.module

import com.ame.lojinhatesteame.data.repository.*
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {
    @Binds
    fun bindBannerRepository(bannerRepositoryImpl: BannerRepositoryImpl): BannerRepository

    @Binds
    fun bindCategoryRepository(categoryRepositoryImpl: CategoryRepositoryImpl): CategoryRepository

    @Binds
    fun bindProductRepository(productRepositoryImpl: ProductRepositoryImpl): ProductRepository
}
