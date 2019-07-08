package br.com.igorfs.lodjinha.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.igorfs.lodjinha.service.ProductService
import br.com.igorfs.lodjinha.util.RetrofitFactory
import br.com.igorfs.lodjinha.util.callback
import br.com.igorfs.lodjinha.vo.ProductVo

class ProductRepository {

    private val topSellers = MutableLiveData<List<ProductVo>>()
    private val productsList = MutableLiveData<List<ProductVo>>()
    private val productService: ProductService by lazy {
        RetrofitFactory().getRetrofit().create(ProductService::class.java)
    }

    companion object {
        private const val TAG = "ProductRepository"
    }

    fun getTopSellersList(): LiveData<List<ProductVo>> = topSellers

    fun fetchTopSellers() {
        productService.getTopSellers().enqueue(callback { response, throwable ->
            response?.let {
                response.body()?.let { topSellers.postValue(it.getList) }
            }
            throwable?.let {
                Log.e(TAG, it.message)
            }
        })
    }

    fun getProductsList(): LiveData<List<ProductVo>> = productsList

    fun fetchProductsData(categoryId: Long? = null, page: Long? = null) {
        productService.getProducts(categoryId = categoryId, offset = page).enqueue(
            callback { response, throwable ->
                response?.let {
                    response.body()?.let { productsList.postValue(it.getList) }
                }
                throwable?.let {
                    Log.e(TAG, it.message)
                }
            })
    }
}