package com.ame.lojinhatesteame

import com.ame.lojinhatesteame.injection.Injector
import com.ame.lojinhatesteame.injection.component.DaggerAppComponent
import com.ame.lojinhatesteame.util.PicassoImageLoadingService
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import ss.com.bannerslider.Slider

class LojinhaApplication : DaggerApplication() {

    companion object {
        lateinit var instance: LojinhaApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        Injector.init(this)
        Slider.init(PicassoImageLoadingService())
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }
}