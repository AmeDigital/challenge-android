package com.ame.lojinhatesteame.injection.module

import com.ame.lojinhatesteame.ui.banner.BannerFragment
import com.ame.lojinhatesteame.ui.category.CategoryFragment
import com.ame.lojinhatesteame.ui.product.ProductBestSalesFragment
import com.ame.lojinhatesteame.ui.product.ProductByCategoryFragment
import com.ame.lojinhatesteame.ui.product.ProductDetailFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FragmentModule {
    @ContributesAndroidInjector
    fun bannerFragment(): BannerFragment

    @ContributesAndroidInjector
    fun categoryFragment(): CategoryFragment

    @ContributesAndroidInjector
    fun productBestSalesFragment(): ProductBestSalesFragment

    @ContributesAndroidInjector
    fun productByCategoryFragment(): ProductByCategoryFragment

    @ContributesAndroidInjector
    fun productDetailFragment(): ProductDetailFragment
}
