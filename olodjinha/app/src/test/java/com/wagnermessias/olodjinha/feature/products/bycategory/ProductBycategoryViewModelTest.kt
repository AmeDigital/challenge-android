package com.wagnermessias.olodjinha.feature.products.bycategory

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.wagnermessias.olodjinha.core.interactor.LoadProductByCategoryInteractor
import com.wagnermessias.olodjinha.core.model.Product
import com.wagnermessias.olodjinha.core.model.Products
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers.Unconfined
import org.junit.Rule
import org.junit.Test
import retrofit2.Response
import java.io.IOException

class ProductBycategoryViewModelTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val interactorLoadProductByCategoryMock = mockk<LoadProductByCategoryInteractor>()
    private val viewModel = ProductsByCategoryViewModel(interactorLoadProductByCategoryMock, Unconfined)

    @Test
    fun responseSuccessfulTrueAndProducstNoEmptyList_LoadProductsByCategory_ExposeProductsByCategoryListState() {
        val productsList = ArrayList<Product>()
        val product = mockk<Product>()
        productsList.add(product)
        val expectedProductsByCategoryListState = ProductsByCategoryViewState.ProductsByCategoryList(productsList)
        val responseMock = mockk<Response<Products>>()
        coEvery { interactorLoadProductByCategoryMock.execute(any(), any()) } returns responseMock
        every { responseMock.isSuccessful } returns true
        every { responseMock.body()?.list } returns productsList


        viewModel.loadProductsByCategory(1, 0)

        assertEquals(viewModel.byCategoryViewState.value, expectedProductsByCategoryListState)
    }

    @Test
    fun responseSuccessfulTrueAndProductsEmptyList_LoadProductsByCategory_ExposeEmptyListState() {
        val productsList = ArrayList<Product>()
        val expectedEmptyListState = ProductsByCategoryViewState.EmptyList
        val responseMock = mockk<Response<Products>>()
        coEvery { interactorLoadProductByCategoryMock.execute(any(), any()) } returns responseMock
        every { responseMock.isSuccessful } returns true
        every { responseMock.body()?.list } returns productsList

        viewModel.loadProductsByCategory(1, 0)

        assertEquals(viewModel.byCategoryViewState.value, expectedEmptyListState)
    }

    @Test
    fun responseSuccessfulFalse_LoadProductsByCategory_ExposeServerErrorState() {
        val expectedServerErrorState = ProductsByCategoryViewState.ServerError
        val responseMock = mockk<Response<Products>>()
        coEvery { interactorLoadProductByCategoryMock.execute(any(), any()) } returns responseMock
        every { responseMock.isSuccessful } returns false

        viewModel.loadProductsByCategory(1, 0)

        assertEquals(viewModel.byCategoryViewState.value, expectedServerErrorState)
    }

    @Test
    fun networkException_LoadProductsByCategory_ExposeNetworkErrorState() {
        val expectedNetworkErrorState = ProductsByCategoryViewState.NetworkError
        coEvery { interactorLoadProductByCategoryMock.execute(any(), any()) } throws IOException()

        viewModel.loadProductsByCategory(1, 0)

        assertEquals(viewModel.byCategoryViewState.value, expectedNetworkErrorState)
    }
}