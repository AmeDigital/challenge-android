package com.ame.lojinhatesteame.injection.component

import android.app.Application
import com.ame.lojinhatesteame.LojinhaApplication
import com.ame.lojinhatesteame.injection.module.FragmentModule
import com.ame.lojinhatesteame.injection.module.RepositoryModule
import com.ame.lojinhatesteame.injection.module.NetWorkModule
import com.ame.lojinhatesteame.injection.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    NetWorkModule::class,
    FragmentModule::class,
    ViewModelModule::class,
    RepositoryModule::class,
    AndroidSupportInjectionModule::class
])
interface AppComponent : AndroidInjector<LojinhaApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): AppComponent.Builder

        fun build(): AppComponent
    }
}
