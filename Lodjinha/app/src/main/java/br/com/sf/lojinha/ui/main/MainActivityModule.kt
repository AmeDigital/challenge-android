package br.com.sf.lojinha.ui.main

import androidx.appcompat.app.AppCompatActivity
import br.com.sf.lojinha.injection.PerActivity
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {

    @PerActivity
    @Provides
    internal fun provideMainView(mainActivity: MainActivity): MainContract.View {
        return mainActivity
    }

    @PerActivity
    @Provides
    internal fun provideMainPresenter(
        view: MainContract.View,
        navigator: MainContract.Navigator
    ): MainContract.Presenter {
        return MainPresenter(view, navigator)
    }

    @PerActivity
    @Provides
    internal fun provideMainNavigator(mainActivity: MainActivity): MainContract.Navigator {
        return MainNavigator(mainActivity)
    }

    @Provides
    @PerActivity
    internal fun appCompatActivity(activity: MainActivity): AppCompatActivity {
        return activity
    }

}