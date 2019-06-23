package com.leonardoalves.ametest.store.category

import com.leonardoalves.ametest.store.viewmodel.StoreCategoryViewModel
import com.leonardoalves.ametest.store.viewmodel.StoreProductViewModel
import com.leonardoalves.repository.repository.StoreRepository
import io.reactivex.disposables.CompositeDisposable

class CategoryPresenter(private val repository: StoreRepository, private val view: CategoryView) {

    private var category: StoreCategoryViewModel? = null
    private var page: Int = 0
    private val pageSize = 20
    private var loading = false
    private var completed = false

    private val compositeDisposable = CompositeDisposable()

    fun onCreate(categoryViewModel: StoreCategoryViewModel) {
        category = categoryViewModel
        view.setupToolbar(categoryViewModel.description)
        category?.id?.let { categoryId ->
            getFirstPage(categoryId)
        }
    }

    private fun getFirstPage(categoryId: Int) {
        compositeDisposable.add(
            repository.getCategoryProducts(0, pageSize, categoryId)
                .map {
                    it.map { product ->
                        StoreProductViewModel(
                            product.id, product.nome ?: "",
                            product.precoDe, product.precoPor ?: 0F, product.urlImagem, product.descricao ?: "",
                            product.categoria?.descricao ?: ""
                        )
                    }
                }
                .doOnSubscribe {
                    view.startLoading()
                    loading = true
                }
                .doOnTerminate {
                    view.stopLoading()
                    loading = false
                }
                .subscribe({
                    view.addItems(it, true)
                    if (it.size < pageSize) completed = true
                }, {
                    it.printStackTrace()
                    completed = true
                }, { page++ })
        )
    }

    fun refresh() {
        view.addItems(listOf(), true)
        compositeDisposable.clear()
        completed = false
        loading = false
        page = 0
        category?.id?.let { categoryId ->
            getFirstPage(categoryId)
        }
    }

    fun onScrollBeyond() {
        if (completed || loading) return
        category?.id?.let { categoryId ->
            compositeDisposable.add(
                repository.getCategoryProducts(page * pageSize, pageSize, categoryId)
                    .map {
                        it.map { product ->
                            StoreProductViewModel(
                                product.id, product.nome ?: "",
                                product.precoDe, product.precoPor ?: 0F, product.urlImagem, product.descricao ?: "",
                                product.categoria?.descricao ?: ""
                            )
                        }
                    }
                    .doOnSubscribe {
                        view.startLoading()
                        loading = true
                    }
                    .doOnTerminate {
                        view.stopLoading()
                        loading = false
                    }
                    .subscribe({
                        view.addItems(it)
                        if (it.size < pageSize) completed = true
                    }, {
                        it.printStackTrace()
                        completed = true
                    }, { page++ })
            )
        }
    }

}