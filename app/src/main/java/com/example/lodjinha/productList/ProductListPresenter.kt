package com.example.lodjinha.productList

import com.example.domain.models.ProductInfo
import com.example.domain.usecases.product.ProductList
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException

class ProductListPresenter(val view: ProductListContract.View,
                           val listProducts: ProductList) :
    ProductListContract.Presenter {
    override fun getProductsList(productInfo: ProductInfo) {
        view.showLoading()
        val a = listProducts.execute(productInfo)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view.hideLoading()
                view.showProductsList(it)
            }, {
                if (it is HttpException) {
                }
            })
    }
}

