package com.lodjinha

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.test.filters.SmallTest
import com.lodjinha.category.CategoryViewModel
import com.lodjinha.category.GetProductsUseCase
import com.lodjinha.common_teste.FakeData.createFakeProducts
import com.lodjinha.model.Products
import com.lodjinha.repository.AppDispatchers
import com.lodjinha.repository.utils.Resource
import com.lodjinha.utils.Event
import io.mockk.coEvery
import io.mockk.confirmVerified
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
@ExperimentalCoroutinesApi
@SmallTest
class CategoryUnitTests {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var getProductsUseCase: GetProductsUseCase
    private lateinit var categoryViewModel: CategoryViewModel

    private val appDispatchers = AppDispatchers(Dispatchers.Unconfined, Dispatchers.Unconfined)

    @Before
    fun setUp() {
        getProductsUseCase = mockk()
    }

    @Test
    fun `Get products requested ViewModel`() {
        val observer = mockk<Observer<Resource<Products>>>(relaxed = true)
        val fakeProducts = createFakeProducts()
        val result = Resource.success(fakeProducts)
        coEvery {
            getProductsUseCase(page = 0, categoryId = 1)
        } returns MutableLiveData<Resource<Products>>().apply {
            value = result
        }

        categoryViewModel = CategoryViewModel(getProductsUseCase = getProductsUseCase, dispatchers = appDispatchers)
        categoryViewModel.configCategory(1)
        categoryViewModel.getProducts(0)
        categoryViewModel.products.observeForever(observer)

        verify {
            observer.onChanged(result)
        }
        confirmVerified(observer)
    }

    @Test
    fun `Get products requested but failed`() {
        val observer = mockk<Observer<Resource<Products>>>(relaxed = true)
        val observerSnackBar = mockk<Observer<Event<Int>>>(relaxed = true)
        val result = Resource.error(Exception("fail"), null)
        coEvery {
            getProductsUseCase(page = 0, categoryId = 1)
        } returns MutableLiveData<Resource<Products>>().apply {
            value = result
        }

        categoryViewModel = CategoryViewModel(getProductsUseCase = getProductsUseCase, dispatchers = appDispatchers)
        categoryViewModel.configCategory(1)
        categoryViewModel.getProducts(0)
        categoryViewModel.products.observeForever(observer)
        categoryViewModel.snackBarError.observeForever(observerSnackBar)

        verify {
            observer.onChanged(result)
            observerSnackBar.onChanged(categoryViewModel.snackBarError.value)
        }
        confirmVerified(observer)
    }
}
