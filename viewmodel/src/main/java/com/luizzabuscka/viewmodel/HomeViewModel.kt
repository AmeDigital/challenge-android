package com.luizzabuscka.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.luizzabuscka.commons.model.Banner
import com.luizzabuscka.commons.model.Category
import com.luizzabuscka.commons.model.Product
import com.luizzabuscka.data.repository.BannersRepository
import com.luizzabuscka.data.repository.CategoriesRepository
import com.luizzabuscka.data.repository.ProductsRepository
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.CompositeDisposable



open class HomeViewModel : ViewModel() {

    private val bannersRepository = BannersRepository()
    private var banners = MutableLiveData<List<Banner>>()

    private val categoriesRepository = CategoriesRepository()
    private var categories = MutableLiveData<List<Category>>()

    private val productsRepository = ProductsRepository()
    private var products = MutableLiveData<List<Product>>()

    fun getBanners(): LiveData<List<Banner>> {
        val compositeDisposable = CompositeDisposable()

        bannersRepository.getBanners(object: SingleObserver<List<Banner>> {
            override fun onSuccess(response: List<Banner>) {
                banners.value = response
                compositeDisposable.clear()
            }
            override fun onSubscribe(d: Disposable) {
                compositeDisposable.add(d)
            }
            override fun onError(e: Throwable) {
                banners.value = null
                compositeDisposable.clear()
            }
        })
        return banners
    }

    fun getCategories(): LiveData<List<Category>> {
        val compositeDisposable = CompositeDisposable()

        categoriesRepository.getCategories(object: SingleObserver<List<Category>> {
            override fun onSuccess(response: List<Category>) {
                categories.value = response
                compositeDisposable.clear()
            }
            override fun onSubscribe(d: Disposable) {
                compositeDisposable.add(d)
            }
            override fun onError(e: Throwable) {
                categories.value = null
                compositeDisposable.clear()
            }
        })
        return categories
    }

    fun getBestSellers(): LiveData<List<Product>> {
        val compositeDisposable = CompositeDisposable()

        productsRepository.getBestSellers(object: SingleObserver<List<Product>> {
            override fun onSuccess(response: List<Product>) {
                products.value = response
                compositeDisposable.clear()
            }
            override fun onSubscribe(d: Disposable) {
                compositeDisposable.add(d)
            }
            override fun onError(e: Throwable) {
                products.value = null
                compositeDisposable.clear()
            }
        })
        return products
    }

}