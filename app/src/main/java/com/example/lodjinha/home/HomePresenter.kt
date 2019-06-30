package com.example.lodjinha.home

import com.example.domain.usecases.banner.BannerList
import com.example.domain.usecases.bestSeller.BestSellerList
import com.example.domain.usecases.category.CategoryList
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException

class HomePresenter(val view: HomeContract.View,
                    val listBanner: BannerList,
                    val listCategories: CategoryList,
                    val listBestSellers: BestSellerList
) : HomeContract.Presenter {
    override fun getBannerList() {
        val a = listBanner.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view.showBannerList(it)
            },{
                if (it is HttpException) {
                }
            })
    }

    override fun getBestSellersList() {
        val a = listBestSellers.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view.showBestSellersList(it)
            },{
                if (it is HttpException) {
                }
            })
    }

    override fun getCategoriesList() {
        val a = listCategories.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view.showCategoriesList(it)
            },{
                if (it is HttpException) {
                }
            })
    }
}