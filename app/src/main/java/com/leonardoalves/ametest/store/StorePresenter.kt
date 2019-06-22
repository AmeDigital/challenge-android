package com.leonardoalves.ametest.store

import android.util.Log
import com.leonardoalves.ametest.store.viewmodel.BannerItemViewModel
import com.leonardoalves.ametest.store.viewmodel.StoreBannerViewModel
import com.leonardoalves.repository.repository.StoreRepository
import io.reactivex.disposables.CompositeDisposable

class StorePresenter(
    private val repository: StoreRepository,
    private val view: StoreView) {

    private val compositeDisposable = CompositeDisposable()

    fun onCreate(){
        compositeDisposable.add(
            repository.getBanners()
                .map { StoreBannerViewModel(it.map { banner -> BannerItemViewModel(banner.urlImagem?:"", banner.linkUrl?:"") }) }
                .map { listOf(it) }
                .subscribe ({
                    Log.e("Received", it[0].bannerList.size.toString())
                    view.setItems(it)
                },{it.printStackTrace()},{})
        )
    }

    fun onDestroy(){
        compositeDisposable.clear()
    }
}