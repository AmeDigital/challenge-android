package br.com.sf.lojinha.injection.app

import br.com.sf.lojinha.injection.PerActivity
import br.com.sf.lojinha.ui.main.MainActivity
import br.com.sf.lojinha.ui.main.MainActivityModule
import br.com.sf.lojinha.ui.main.MainActivityProvider
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class ActivityBuilder {

    @PerActivity
    @ContributesAndroidInjector(modules = [MainActivityModule::class, MainActivityProvider::class])
    internal abstract fun bindMainActivity(): MainActivity

}
