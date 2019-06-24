package com.ame.lojinhatesteame.ui.product

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.BindingAdapter
import android.databinding.ObservableBoolean
import android.widget.ImageView
import com.ame.lojinhatesteame.LojinhaApplication
import com.ame.lojinhatesteame.R
import com.ame.lojinhatesteame.data.model.Product
import com.ame.lojinhatesteame.data.repository.ProductRepository
import com.ame.lojinhatesteame.util.SingleLiveEvent
import com.squareup.picasso.Picasso
import javax.inject.Inject

class ProductViewModel @Inject constructor(private val productRepository: ProductRepository) : ViewModel() {

    var productLiveData = MutableLiveData<MutableList<Product>>()
    val isProgressBarVisible = ObservableBoolean()
    val showToast = SingleLiveEvent<String>()
    var product = Product()


    init {
        isProgressBarVisible.set(false)
    }

    companion object {
        @BindingAdapter( "productImage")
        @JvmStatic
        fun loadRemoteImage(iv: ImageView, url: String ){
            Picasso.get().load(url).into(iv)
        }
    }

    fun onLoadBestSales() {
        isProgressBarVisible.set(true)
        productRepository.loadProductsBestSales(ProductCallbacks())
    }

    fun onLoadProductByCategory(categoryId: Int) {
        isProgressBarVisible.set(true)
        productRepository.loadProductsByCategory(categoryId, ProductCallbacks())
    }

    fun onReserve(productId: Int) {
        productRepository.reserve(productId, ProductCallbacks())
    }

    inner class ProductCallbacks : ProductRepository.ProductCallbacks {
        override fun onLoadProductByCategorySuccess(products: MutableList<Product>) {
            isProgressBarVisible.set(false)
            productLiveData.value = products.toMutableList()
        }

        override fun onLoadProductByCategoryError(errorMessage: String) {
            isProgressBarVisible.set(false)
            showToast.value = errorMessage
        }

        override fun onLoadBestSalesSuccess(products: MutableList<Product>) {
            isProgressBarVisible.set(false)
            productLiveData.value = products.toMutableList()
        }

        override fun onLoadBestSalesError(errorMessage: String) {
            isProgressBarVisible.set(false)
            showToast.value = errorMessage
        }

        override fun onReserveSuccess() {
            isProgressBarVisible.set(false)
            showToast.value =  LojinhaApplication.instance.getString(R.string.salve_success)
        }

        override fun onReserveError(errorMessage: String) {
            isProgressBarVisible.set(false)
            showToast.value = errorMessage
        }

    }
}