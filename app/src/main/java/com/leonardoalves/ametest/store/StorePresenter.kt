package com.leonardoalves.ametest.store

import com.leonardoalves.ametest.R
import com.leonardoalves.ametest.custom.ViewModel
import com.leonardoalves.ametest.store.viewmodel.*
import com.leonardoalves.repository.repository.StoreRepository
import io.reactivex.disposables.CompositeDisposable

class StorePresenter(
    private val repository: StoreRepository,
    private val view: StoreView) {

    private val compositeDisposable = CompositeDisposable()

    private val observable = repository.getBanners()
        .map {
            StoreBannerViewModel(it.map { banner ->
                BannerItemViewModel(
                    banner.urlImagem ?: "",
                    banner.linkUrl ?: ""
                )
            })
        }
        .map { arrayListOf<ViewModel>(it) }
        .map { it.add(StoreHeaderViewModel(R.string.stores_categories)); it }
        .flatMap { result ->
            repository.getCategories()
                .map {
                    it.map { category ->
                        StoreCategoryViewModel(
                            category.id,
                            category.urlImagem ?: "",
                            category.descricao ?: ""
                        )
                    }
                }
                .map { StoreCategoriesListViewModel(it) }
                .map { result.add(it); result }
        }
        .map { it.add(StoreHeaderViewModel(R.string.stores_best_sellers)); it }
        .flatMap { result ->
            repository.getBestSellers()
                .map {
                    it.map { product ->
                        StoreProductViewModel(
                            product.id, product.nome ?: "",
                            product.precoDe, product.precoPor ?: 0F, product.urlImagem, product.descricao ?: "",
                            product.categoria?.descricao ?: ""
                        )
                    }
                }
                .map { result.addAll(it); result }
        }

    fun onCreate(){
        compositeDisposable.add(
            observable
                .doOnSubscribe { view.startLoading() }
                .doOnTerminate { view.stopLoading() }
                .subscribe({
                    view.setItems(it)
                }, { it.printStackTrace() }, {})
        )
    }

    fun onDestroy(){
        compositeDisposable.clear()
    }

    fun refresh() {
        view.setItems(listOf())
        compositeDisposable.add(
            observable
                .doOnTerminate { view.stopLoading() }
                .subscribe({
                    view.setItems(it)
                }, { it.printStackTrace() }, {})
        )
    }
}