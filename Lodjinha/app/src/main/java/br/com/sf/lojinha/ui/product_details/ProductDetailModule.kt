package br.com.sf.lojinha.ui.product_details

import br.com.sf.lojinha.injection.PerFragment
import dagger.Binds
import dagger.Module

@Module
abstract class ProductDetailModule {

    @PerFragment
    @Binds
    internal abstract fun providePresenter(presenter: ProductDetailPresenter): ProductDetailContract.Presenter

    @PerFragment
    @Binds
    internal abstract fun provideView(fragment: ProductDetailFragment): ProductDetailContract.View

    @PerFragment
    @Binds
    internal abstract fun provideNavigator(navigator: ProductDetailNavigator): ProductDetailContract.Navigator

}
