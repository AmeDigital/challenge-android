package com.leonardoalves.ametest.store

import android.util.Log
import com.leonardoalves.ametest.R
import com.leonardoalves.ametest.custom.ViewModel
import com.leonardoalves.ametest.store.viewmodel.BannerItemViewModel
import com.leonardoalves.ametest.store.viewmodel.HeaderViewModel
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
                .map { arrayListOf<ViewModel>(it) }
                .map { it.add(HeaderViewModel(R.string.stores_categories)); it}
                .subscribe ({
                    view.setItems(it)
                },{it.printStackTrace()},{})
        )
    }

    fun onDestroy(){
        compositeDisposable.clear()
    }
}