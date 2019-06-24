package com.ame.lojinhatesteame.injection.module

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.ame.lojinhatesteame.injection.ViewModelFactory
import com.ame.lojinhatesteame.injection.ViewModelKey
import com.ame.lojinhatesteame.ui.banner.BannerViewModel
import com.ame.lojinhatesteame.ui.category.CategoryViewModel
import com.ame.lojinhatesteame.ui.product.ProductViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {
    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(BannerViewModel::class)
    fun bindBannerViewModel(viewModel: BannerViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CategoryViewModel::class)
    fun bindCategoryViewModel(viewModel: CategoryViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProductViewModel::class)
    fun bindProductViewModel(viewModel: ProductViewModel): ViewModel
}