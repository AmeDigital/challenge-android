package com.ame.lojinhatesteame.data.repository

import com.ame.lojinhatesteame.LojinhaApplication
import com.ame.lojinhatesteame.R
import com.ame.lojinhatesteame.data.executor.disk
import com.ame.lojinhatesteame.data.executor.network
import com.ame.lojinhatesteame.data.executor.ui
import com.ame.lojinhatesteame.data.model.Product
import com.ame.lojinhatesteame.data.remote.ApiService
import com.ame.lojinhatesteame.data.remote.response.BodyResponseProduct
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(private val apiService: ApiService) : ProductRepository {

    override fun loadProductsByCategory(categoryId: Int, callbacks: ProductRepository.ProductCallbacks) {
        network {
            try {
                val productsResponse: Response<BodyResponseProduct> = apiService.getProductByCategory(categoryId).execute()
                if (productsResponse.isSuccessful) {
                    val responseBody = productsResponse.body()
                    val products = mutableListOf<Product>()
                    if (responseBody != null) {
                        disk {
                            responseBody.apply {
                                data.forEach {
                                    products.add(it)
                                }
                                ui {callbacks.onLoadProductByCategorySuccess(products)}
                            }
                        }
                    } else {
                        ui {callbacks.onLoadProductByCategoryError(LojinhaApplication.instance.getString(R.string.unexpected_error))}
                    }
                } else {
                    ui{callbacks.onLoadProductByCategoryError(productsResponse.errorBody().toString())}
                }
            } catch (e: Exception) {
                e.message?.apply {
                    ui{callbacks.onLoadProductByCategoryError(this)}
                }
            }
        }
    }

    override fun loadProductsBestSales(callbacks: ProductRepository.ProductCallbacks) {
        network {
            try {
                val productsResponse: Response<BodyResponseProduct> = apiService.getProductsBestSales().execute()
                if (productsResponse.isSuccessful) {
                    val responseBody = productsResponse.body()
                    val products = mutableListOf<Product>()
                    if (responseBody != null) {
                        disk {
                            responseBody.apply {
                                data.forEach {
                                    products.add(it)
                                }
                               ui{ callbacks.onLoadBestSalesSuccess(products)}
                            }
                        }
                    } else {
                        ui{callbacks.onLoadBestSalesError(LojinhaApplication.instance.getString(R.string.unexpected_error))}
                    }
                } else {
                    ui{callbacks.onLoadBestSalesError(productsResponse.errorBody().toString())}
                }
            } catch (e: IOException) {
                e.message?.apply {
                    ui{callbacks.onLoadBestSalesError(this)}
                }
            }
        }
    }

    override fun reserve(productId: Int, callbacks: ProductRepository.ProductCallbacks) {
        network {
            try {
                val reserveResponse: Response<Void> = apiService.setReserve(productId).execute()
                if (reserveResponse.isSuccessful) {
                    callbacks.onReserveSuccess()
                } else {
                    ui{callbacks.onReserveError(reserveResponse.errorBody().toString())}
                }
            } catch (e: IOException) {
                e.message?.apply {
                    ui{callbacks.onReserveError(this)}
                }
            }
        }
    }
}