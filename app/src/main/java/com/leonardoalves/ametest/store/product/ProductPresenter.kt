package com.leonardoalves.ametest.store.product

import com.leonardoalves.ametest.store.viewmodel.StoreProductViewModel
import com.leonardoalves.repository.repository.StoreRepository
import io.reactivex.disposables.CompositeDisposable

class ProductPresenter(private val repository: StoreRepository, private val view: ProductView) {
    private val compositeDisposable = CompositeDisposable()

    private var currentProductId = -1

    fun onCreate(productId: Int) {
        compositeDisposable.add(
            repository.getProduct(productId)
                .map { product ->
                    currentProductId = product.id
                    StoreProductViewModel(
                        product.id, product.nome ?: "",
                        product.precoDe, product.precoPor ?: 0F, product.urlImagem, product.descricao ?: "",
                        product.categoria?.descricao ?: ""
                    )
                }.subscribe({
                    view.fillProductDetails(it)
                }, {
                    view.showErrorCriticalMessage()
                    it.printStackTrace()
                }, {})
        )
    }

    fun reserveItem() {
        compositeDisposable.add(
            repository.reserveProduct(currentProductId)
                .subscribe({
                    view.showReservationSuccessMessage()
                }, {
                    view.showReservationErrorMessage()
                    it.printStackTrace()
                })
        )
    }

    fun onDestroy() {
        compositeDisposable.clear()
    }

}