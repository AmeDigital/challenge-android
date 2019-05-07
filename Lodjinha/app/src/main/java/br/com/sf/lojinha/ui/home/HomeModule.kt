package br.com.sf.lojinha.ui.home

import br.com.sf.lojinha.injection.PerFragment
import dagger.Binds
import dagger.Module

@Module
abstract class HomeModule {

    @PerFragment
    @Binds
    internal abstract fun providePresenter(presenter: HomePresenter): HomeContract.Presenter

    @PerFragment
    @Binds
    internal abstract fun provideView(fragment: HomeFragment): HomeContract.View

    @PerFragment
    @Binds
    internal abstract fun provideNavigator(navigator: HomeNavigator): HomeContract.Navigator

}
