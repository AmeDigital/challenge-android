package com.example.lodjinha.di

import com.example.lodjinha.home.HomeContract
import com.example.lodjinha.home.HomePresenter
import com.example.lodjinha.product.ProductContract
import com.example.lodjinha.product.ProductPresenter
import com.example.lodjinha.productList.ProductListContract
import com.example.lodjinha.productList.ProductListPresenter
import org.koin.dsl.module

object PresenterModule {
    val presenterModule = module {
        factory <HomeContract.Presenter>{ (view: HomeContract.View) ->
            HomePresenter(
                view = view,
                listBanner = get(),
                listCategories = get(),
                listBestSellers = get()
            )
        }

        factory <ProductContract.Presenter>{ (view: ProductContract.View) ->
            ProductPresenter(
                view = view,
                product = get(),
                reserveProduct = get()
            )
        }

        factory <ProductListContract.Presenter>{ (view: ProductListContract.View) ->
            ProductListPresenter(
                view = view,
                listProducts = get()
            )
        }
    }
}