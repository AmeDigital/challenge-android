package br.com.sf.lojinha.ui.products_list

import br.com.sf.lojinha.injection.PerFragment
import dagger.Binds
import dagger.Module

@Module
abstract class ProductsModule {

    @PerFragment
    @Binds
    internal abstract fun providePresenter(presenter: ProductsPresenter): ProductsContract.Presenter

    @PerFragment
    @Binds
    internal abstract fun provideView(fragment: ProductsFragment): ProductsContract.View

    @PerFragment
    @Binds
    internal abstract fun provideNavigator(navigator: ProductsNavigator): ProductsContract.Navigator

}
