package br.com.sf.lojinha.app

import android.app.Activity
import android.os.Build
import androidx.appcompat.app.AppCompatDelegate
import androidx.multidex.MultiDexApplication
import br.com.sf.lojinha.injection.app.DaggerAppComponent
import br.com.sf.lojinha.ui.PicassoImageLoadingService
import butterknife.ButterKnife
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import io.reactivex.plugins.RxJavaPlugins
import ss.com.bannerslider.Slider
import timber.log.Timber
import javax.inject.Inject

class ApplicationController : MultiDexApplication(), HasActivityInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent
            .builder()
            .application(this)
            .build()
            .inject(this)

        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) {
            AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        }

        Slider.init(PicassoImageLoadingService())
        RxJavaPlugins.setErrorHandler { Timber.e(it) }
        ButterKnife.setDebug(true)
    }

    override fun activityInjector(): AndroidInjector<Activity>? {
        return activityInjector
    }

}
