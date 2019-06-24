package com.ame.lojinhatesteame.data.repository

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.ame.lojinhatesteame.data.model.Category
import com.ame.lojinhatesteame.data.model.Product
import com.ame.lojinhatesteame.data.remote.ApiService
import com.ame.lojinhatesteame.data.remote.response.BodyResponseProduct
import okhttp3.MediaType
import okhttp3.ResponseBody

import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.*
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Call
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class ProductRepositoryImplTest {

    @Rule
    @JvmField
    var instantExecutor = InstantTaskExecutorRule()

    @Mock
    private lateinit var apiService: ApiService

    @Mock
    private lateinit var call: Call<BodyResponseProduct>

    @Mock
    private lateinit var callReserve: Call<Void>

    @Mock
    private lateinit var productCallbacks: ProductRepository.ProductCallbacks

    @InjectMocks
    private lateinit var productRepository: ProductRepositoryImpl

    private val product = Product().apply {
        id = 7
        name = "Fifa 17"
        description = "Games"
        urlImage = "http://39ahd9aq5l9101brf3b8dq58.wpengine.netdna-cdn.com/wp-content/uploads/2013/06/3D-Gaming.png"
        priceOf = 299.0
        priceFor = 119.9899999999999948840923025272786617279052734375
    }
    private val products = ArrayList<Product>().apply { add(product) }

    private val category = Category().apply {
        id = 1
        description = "Games"
        urlImage = "http://39ahd9aq5l9101brf3b8dq58.wpengine.netdna-cdn.com/wp-content/uploads/2013/06/3D-Gaming.png"
    }

    private val messageError = "Authorization Required"

    @Test
    fun productsBestSalesApiCall() {
        `when`(apiService.getProductsBestSales()).thenReturn(call)
        `when`(call.execute()).thenReturn(Response.success(BodyResponseProduct(products)))

        productRepository.loadProductsBestSales(productCallbacks)

        Mockito.verify(apiService, after(5).atMost(4)).getProductsBestSales()
        Mockito.verify(call, after(5).atMost(4)).execute()

        verifyNoMoreInteractions(apiService)
        verifyNoMoreInteractions(call)
    }

    @Test
    fun productsByCategoryApiCall() {
        `when`(apiService.getProductByCategory(category.id)).thenReturn(call)
        `when`(call.execute()).thenReturn(Response.success(BodyResponseProduct(products)))

        productRepository.loadProductsByCategory(category.id, productCallbacks)

        Mockito.verify(apiService, after(5).atMost(4)).getProductByCategory(category.id)
        Mockito.verify(call, after(5).atMost(4)).execute()

        verifyNoMoreInteractions(apiService)
        verifyNoMoreInteractions(call)
    }

    @Test
    fun reserveApiCall() {
        `when`(apiService.setReserve(product.id)).thenReturn(callReserve)
        `when`(callReserve.execute()).thenReturn(Response.success(null))

        productRepository.reserve(product.id, productCallbacks)

        verify(apiService,after(5).atMost(4)).setReserve(product.id)
        verify(callReserve, after(5).atMost(4)).execute()

        verifyNoMoreInteractions(apiService)
        verifyNoMoreInteractions(callReserve)
    }

    @Test
    fun productBestSalesSuccessCallback() {
        `when`(apiService.getProductsBestSales()).thenReturn(call)
        `when`(call.execute()).thenReturn(Response.success(BodyResponseProduct(products)))

        productRepository.loadProductsBestSales(productCallbacks)
        verify(productCallbacks, after(5).atMost(4)).onLoadBestSalesSuccess(products.toMutableList())
    }

    @Test
    fun productByCategorySuccessCallback() {
        `when`(apiService.getProductByCategory(category.id)).thenReturn(call)
        `when`(call.execute()).thenReturn(Response.success(BodyResponseProduct(products)))

        productRepository.loadProductsByCategory(category.id, productCallbacks)

        verify(productCallbacks, after(5).atMost(4)).onLoadProductByCategorySuccess(products.toMutableList())
    }

    @Test
    fun reserveSuccessCallback() {
        `when`(apiService.setReserve(product.id)).thenReturn(callReserve)
        `when`(callReserve.execute()).thenReturn(Response.success(null))

        productRepository.reserve(product.id, productCallbacks)

        verify(productCallbacks, after(5).atMost(4)).onReserveSuccess()
    }

    @Test
    fun productBestSalesErrorCallback() {
        val mediaType = MediaType.parse("application/json; charset=utf-8")
        val body = ResponseBody.create(mediaType, "Unauthorized")

        val response = Response.error<BodyResponseProduct>(401, body)

        `when`(apiService.getProductsBestSales()).thenReturn(call)
        `when`(call.execute()).thenReturn(response)

        productRepository.loadProductsBestSales(productCallbacks)
        verify(productCallbacks, after(5).atMost(4)).onLoadBestSalesError(messageError)
    }

    @Test
    fun productByCategoryErrorCallback() {
        val mediaType = MediaType.parse("application/json; charset=utf-8")
        val body = ResponseBody.create(mediaType, "Unauthorized")

        val response = Response.error<BodyResponseProduct>(401, body)

        `when`(apiService.getProductByCategory(category.id)).thenReturn(call)
        `when`(call.execute()).thenReturn(response)

        productRepository.loadProductsByCategory(category.id, productCallbacks)

        verify(productCallbacks, after(5).atMost(4)).onLoadProductByCategoryError(messageError)
    }

    @Test
    fun reserveErrorCallback() {
        val mediaType = MediaType.parse("application/json; charset=utf-8")
        val body = ResponseBody.create(mediaType, "Unauthorized")

        val response = Response.error<Void>(401, body)

        `when`(apiService.setReserve(product.id)).thenReturn(callReserve)
        `when`(callReserve.execute()).thenReturn(response)

        productRepository.reserve(product.id, productCallbacks)

        verify(productCallbacks, after(5).atMost(4)).onReserveError(messageError)
    }
}