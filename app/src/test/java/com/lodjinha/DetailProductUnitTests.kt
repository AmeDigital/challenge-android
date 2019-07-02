package com.lodjinha

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.test.filters.SmallTest
import com.lodjinha.common_teste.FakeData.createFakeProducts
import com.lodjinha.detailProduct.DetailProductViewModel
import com.lodjinha.detailProduct.ReserveProductUseCase
import com.lodjinha.repository.AppDispatchers
import com.lodjinha.repository.utils.Resource
import com.lodjinha.utils.Event
import io.mockk.coEvery
import io.mockk.confirmVerified
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
@ExperimentalCoroutinesApi
@SmallTest
class DetailProductUnitTests {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var reserveProductUseCase: ReserveProductUseCase
    private lateinit var detailProductViewModel: DetailProductViewModel
    private lateinit var responseBody: ResponseBody

    private val appDispatchers = AppDispatchers(Dispatchers.Unconfined, Dispatchers.Unconfined)

    @Before
    fun setUp() {
        reserveProductUseCase = mockk()
        responseBody = mockk()
    }

    @Test
    fun `Reserve products requested ViewModel`() {
        val observer = mockk<Observer<Resource<ResponseBody>>>(relaxed = true)
        val result = Resource.success(responseBody)
        coEvery {
            reserveProductUseCase(7)
        } returns MutableLiveData<Resource<ResponseBody>>().apply {
            value = result
        }

        detailProductViewModel = DetailProductViewModel(reserveProductUseCase = reserveProductUseCase, dispatchers = appDispatchers)
        detailProductViewModel.configProduct(createFakeProducts().data[0])
        detailProductViewModel.reserveProduct()
        detailProductViewModel.response.observeForever(observer)

        verify {
            observer.onChanged(result)
        }
        confirmVerified(observer)
    }

    @Test
    fun `Reserve products requested but failed`() {
        val observer = mockk<Observer<Resource<ResponseBody>>>(relaxed = true)
        val observerSnackBar = mockk<Observer<Event<Int>>>(relaxed = true)
        val result = Resource.error(Exception("fail"), null)
        coEvery {
            reserveProductUseCase(7)
        } returns MutableLiveData<Resource<ResponseBody>>().apply {
            value = result
        }

        detailProductViewModel = DetailProductViewModel(reserveProductUseCase = reserveProductUseCase, dispatchers = appDispatchers)
        detailProductViewModel.configProduct(createFakeProducts().data[0])
        detailProductViewModel.reserveProduct()
        detailProductViewModel.response.observeForever(observer)
        detailProductViewModel.snackBarError.observeForever(observerSnackBar)

        verify {
            observer.onChanged(result)
            observerSnackBar.onChanged(detailProductViewModel.snackBarError.value)
        }
        confirmVerified(observer)
    }
}
