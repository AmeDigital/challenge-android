package com.example.lodjinha.product

import com.example.domain.usecases.product.Product
import com.example.domain.usecases.product.ReserveUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException

class ProductPresenter(val view: ProductContract.View,
                       val product: Product,
                       val reserveProduct : ReserveUseCase
) : ProductContract.Presenter {
    override fun getProduct(productId: Int) {
        val a = product.execute(productId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view.showProduct(it)
            }, {
                if (it is HttpException) {
                }
            })
    }

    override fun reserveProduct(productId: Int) {
        val a = reserveProduct.execute(productId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view.showSuccessAlert()
            }, {
                view.showErrorAlert()
            })
    }
}