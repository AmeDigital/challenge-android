package com.ame.lojinhatesteame.ui.product

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.ame.lojinhatesteame.data.model.Category
import com.ame.lojinhatesteame.data.model.Product
import com.ame.lojinhatesteame.data.repository.ProductRepository
import com.ame.lojinhatesteame.util.any
import com.ame.lojinhatesteame.util.capture
import com.ame.lojinhatesteame.util.eq
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.*
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ProductViewModelTest {

    @Rule
    @JvmField
    val taskExecutorRule = InstantTaskExecutorRule()

    @InjectMocks
    private lateinit var viewModel: ProductViewModel

    @Mock
    private lateinit var repository: ProductRepository

    @Captor
    private lateinit var callbacks: ArgumentCaptor<ProductRepository.ProductCallbacks>

    private val messageError = "erro ao carregar produtos"
    private val messageReserveSucess = "Salvo com sucesso!"
    private val category = Category().apply { id = 1 }
    private val product = Product().apply {
        id = 7
        name = "Fifa 17"
        description = "Games"
        urlImage = "http://39ahd9aq5l9101brf3b8dq58.wpengine.netdna-cdn.com/wp-content/uploads/2013/06/3D-Gaming.png"
        priceOf = 299.0
        priceFor = 119.9899999999999948840923025272786617279052734375
    }

    @Test
    fun callsProductRepository() {
        viewModel.onLoadBestSales()
        Mockito.verify(repository).loadProductsBestSales(any())

        viewModel.onLoadProductByCategory(category.id)
        Mockito.verify(repository).loadProductsByCategory(eq(category.id), any())

        viewModel.onReserve(product.id)
        Mockito.verify(repository).reserve(eq(product.id), any())
    }

    @Test
    fun showsProgressBar() {
        viewModel.onLoadBestSales()
        Assert.assertThat(viewModel.isProgressBarVisible.get(), CoreMatchers.`is`(true))

        viewModel.onLoadProductByCategory(category.id)
        Assert.assertThat(viewModel.isProgressBarVisible.get(), CoreMatchers.`is`(true))

        viewModel.onReserve(product.id)
        Assert.assertThat(viewModel.isProgressBarVisible.get(), CoreMatchers.`is`(true))
    }

    @Test
    fun progressBarIsInvisibleInitially() {
        MatcherAssert.assertThat(viewModel.isProgressBarVisible.get(), CoreMatchers.`is`(false))
    }

    @Test
    fun onLoadBestSalesSuccess() {
        val products = ArrayList<Product>()
        products.add(product)

        viewModel.onLoadBestSales()
        verify(repository).loadProductsBestSales(capture(callbacks))
        callbacks.value.onLoadBestSalesSuccess(products.toMutableList())

        Assert.assertThat(viewModel.isProgressBarVisible.get(), CoreMatchers.`is`(false))
        Assert.assertEquals(viewModel.productLiveData.value?.get(0)?.id, products[0].id)
    }

    @Test
    fun onLoadBestSalesError() {
        viewModel.onLoadBestSales()
        verify(repository).loadProductsBestSales(capture(callbacks))
        callbacks.value.onLoadBestSalesError(messageError)

        Assert.assertThat(viewModel.isProgressBarVisible.get(), CoreMatchers.`is`(false))
        Assert.assertEquals(viewModel.showToast.value, messageError)
    }

    @Test
    fun onLoadProductByCategorySuccess() {
        val products = ArrayList<Product>()
        products.add(product)

        viewModel.onLoadProductByCategory(category.id)
        verify(repository).loadProductsByCategory(eq(category.id), capture(callbacks))
        callbacks.value.onLoadProductByCategorySuccess(products.toMutableList())

        Assert.assertThat(viewModel.isProgressBarVisible.get(), CoreMatchers.`is`(false))
        Assert.assertEquals(viewModel.productLiveData.value?.get(0)?.id, products[0].id)
    }

    @Test
    fun onLoadProductByCategoryError() {
        viewModel.onLoadProductByCategory(category.id)
        verify(repository).loadProductsByCategory(eq(category.id), capture(callbacks))
        callbacks.value.onLoadProductByCategoryError(messageError)

        Assert.assertThat(viewModel.isProgressBarVisible.get(), CoreMatchers.`is`(false))
        Assert.assertEquals(viewModel.showToast.value, messageError)
    }

    @Test
    fun onLoadReserveSuccess() {
        viewModel.onReserve(product.id)
        verify(repository).reserve(eq(product.id), capture(callbacks))
        callbacks.value.onReserveSuccess()

        Assert.assertThat(viewModel.isProgressBarVisible.get(), CoreMatchers.`is`(false))
        Assert.assertEquals(viewModel.showToast.value, messageReserveSucess)
    }

    @Test
    fun onLoadReserveError() {
        viewModel.onReserve(product.id)
        verify(repository).reserve(eq(product.id), capture(callbacks))
        callbacks.value.onReserveError(messageError)

        Assert.assertThat(viewModel.isProgressBarVisible.get(), CoreMatchers.`is`(false))
        Assert.assertEquals(viewModel.showToast.value, messageError)
    }
}