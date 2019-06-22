package com.leonardoalves.ametest.store

import android.util.Log
import com.leonardoalves.ametest.R
import com.leonardoalves.ametest.custom.ViewModel
import com.leonardoalves.ametest.store.viewmodel.*
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
                .flatMap { result ->
                    repository.getCategories()
                        .map { it.map { category -> StoreCategoryViewModel(category.id, category.urlImagem?:"", category.descricao?:"") } }
                        .map { StoreCategoriesListViewModel(it) }
                        .map { result.add(it); result }
                }
                .subscribe ({
                    view.setItems(it)
                },{it.printStackTrace()},{})
        )
    }

    fun onDestroy(){
        compositeDisposable.clear()
    }
}