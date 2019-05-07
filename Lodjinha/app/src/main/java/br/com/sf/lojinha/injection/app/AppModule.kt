package br.com.sf.lojinha.injection.app

import android.app.Application
import android.content.Context
import br.com.sf.lojinha.repository.DataManagerModule
import br.com.sf.lojinha.util.schedulers.BaseSchedulerProvider
import br.com.sf.lojinha.util.schedulers.SchedulerProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [DataManagerModule::class])
internal class AppModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    @Singleton
    fun provideSchedulerProvider(): BaseSchedulerProvider {
        return SchedulerProvider.instance
    }

}