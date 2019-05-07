package br.com.sf.lojinha.repository

import br.com.sf.lojinha.repository.store.StoreDataSource
import br.com.sf.lojinha.repository.store.remote.StoreRemoteDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataManagerModule {

    @Singleton
    @Provides
    fun provideStoreDataSource(): StoreDataSource {
        return StoreRemoteDataSource()
    }

}
