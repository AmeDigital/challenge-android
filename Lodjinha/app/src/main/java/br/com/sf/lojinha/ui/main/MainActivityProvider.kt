package br.com.sf.lojinha.ui.main

import br.com.sf.lojinha.injection.PerFragment
import br.com.sf.lojinha.ui.home.HomeFragment
import br.com.sf.lojinha.ui.home.HomeModule
import br.com.sf.lojinha.ui.product_details.ProductDetailFragment
import br.com.sf.lojinha.ui.product_details.ProductDetailModule
import br.com.sf.lojinha.ui.products_list.ProductsFragment
import br.com.sf.lojinha.ui.products_list.ProductsModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityProvider {

    @PerFragment
    @ContributesAndroidInjector(modules = [HomeModule::class])
    internal abstract fun provideHomeFragment(): HomeFragment

    @PerFragment
    @ContributesAndroidInjector(modules = [ProductsModule::class])
    internal abstract fun provideProductsFragment(): ProductsFragment

    @PerFragment
    @ContributesAndroidInjector(modules = [ProductDetailModule::class])
        internal abstract fun provideProductDetailFragment(): ProductDetailFragment

}
