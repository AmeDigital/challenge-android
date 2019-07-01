package com.lodjinha

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.test.filters.SmallTest
import com.lodjinha.common_teste.FakeData.createFakeBanners
import com.lodjinha.common_teste.FakeData.createFakeCategories
import com.lodjinha.common_teste.FakeData.createFakeProducts
import com.lodjinha.home.BannerUseCase
import com.lodjinha.home.CategoriesUseCase
import com.lodjinha.home.HomeViewModel
import com.lodjinha.home.TopSellingUseCase
import com.lodjinha.model.Banners
import com.lodjinha.model.Categories
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
class HomeUnitTests {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var bannersUseCase: BannerUseCase
    private lateinit var categoriesUseCase: CategoriesUseCase
    private lateinit var topSellingUseCase: TopSellingUseCase

    private lateinit var homeViewModel: HomeViewModel

    private val appDispatchers = AppDispatchers(Dispatchers.Unconfined, Dispatchers.Unconfined)

    @Before
    fun setUp() {
        bannersUseCase = mockk()
        categoriesUseCase = mockk()
        topSellingUseCase = mockk()
    }

    @Test
    fun `Get banner requested ViewModel`() {
        val observer = mockk<Observer<Resource<Banners>>>(relaxed = true)
        val fakeBanners = createFakeBanners()
        val result = Resource.success(fakeBanners)
        coEvery {
            bannersUseCase()
        } returns MutableLiveData<Resource<Banners>>().apply {
            value = result
        }

        homeViewModel = HomeViewModel(bannersUseCase,categoriesUseCase, topSellingUseCase, appDispatchers)
        homeViewModel.banners.observeForever(observer)

        verify {
            observer.onChanged(result)
        }
        confirmVerified(observer)
    }

    @Test
    fun `Get banner requested but failed`() {
        val observer = mockk<Observer<Resource<Banners>>>(relaxed = true)
        val observerSnackBar = mockk<Observer<Event<Int>>>(relaxed = true)
        val result = Resource.error(Exception("fail"), null)
        coEvery {
            bannersUseCase()
        } returns MutableLiveData<Resource<Banners>>().apply {
            value = result
        }

        homeViewModel = HomeViewModel(bannersUseCase,categoriesUseCase, topSellingUseCase, appDispatchers)
        homeViewModel.banners.observeForever(observer)
        homeViewModel.snackBarError.observeForever(observerSnackBar)

        verify {
            observer.onChanged(result)
            observerSnackBar.onChanged(homeViewModel.snackBarError.value)
        }
        confirmVerified(observer)
    }

    @Test
    fun `Get categories requested ViewModel`() {
        val observer = mockk<Observer<Resource<Categories>>>(relaxed = true)
        val fakeCategories = createFakeCategories()
        val result = Resource.success(fakeCategories)
        coEvery {
            categoriesUseCase()
        } returns MutableLiveData<Resource<Categories>>().apply {
            value = result
        }

        homeViewModel = HomeViewModel(bannersUseCase,categoriesUseCase, topSellingUseCase, appDispatchers)
        homeViewModel.categories.observeForever(observer)

        verify {
            observer.onChanged(result)
        }
        confirmVerified(observer)
    }

    @Test
    fun `Get categories requested but failed`() {
        val observer = mockk<Observer<Resource<Categories>>>(relaxed = true)
        val observerSnackBar = mockk<Observer<Event<Int>>>(relaxed = true)
        val result = Resource.error(Exception("fail"), null)
        coEvery {
            categoriesUseCase()
        } returns MutableLiveData<Resource<Categories>>().apply {
            value = result
        }

        homeViewModel = HomeViewModel(bannersUseCase,categoriesUseCase, topSellingUseCase, appDispatchers)
        homeViewModel.categories.observeForever(observer)
        homeViewModel.snackBarError.observeForever(observerSnackBar)

        verify {
            observer.onChanged(result)
            observerSnackBar.onChanged(homeViewModel.snackBarError.value)
        }
        confirmVerified(observer)
    }

    @Test
    fun `Get top selling products requested ViewModel`() {
        val observer = mockk<Observer<Resource<Products>>>(relaxed = true)
        val fakeProducts = createFakeProducts()
        val result = Resource.success(fakeProducts)
        coEvery {
            topSellingUseCase()
        } returns MutableLiveData<Resource<Products>>().apply {
            value = result
        }

        homeViewModel = HomeViewModel(bannersUseCase,categoriesUseCase, topSellingUseCase, appDispatchers)
        homeViewModel.topSelling.observeForever(observer)

        verify {
            observer.onChanged(result)
        }
        confirmVerified(observer)
    }

    @Test
    fun `Get top selling products requested but failed`() {
        val observer = mockk<Observer<Resource<Products>>>(relaxed = true)
        val observerSnackBar = mockk<Observer<Event<Int>>>(relaxed = true)
        val result = Resource.error(Exception("fail"), null)
        coEvery {
            topSellingUseCase()
        } returns MutableLiveData<Resource<Products>>().apply {
            value = result
        }

        homeViewModel = HomeViewModel(bannersUseCase,categoriesUseCase, topSellingUseCase, appDispatchers)
        homeViewModel.topSelling.observeForever(observer)
        homeViewModel.snackBarError.observeForever(observerSnackBar)

        verify {
            observer.onChanged(result)
            observerSnackBar.onChanged(homeViewModel.snackBarError.value)
        }
        confirmVerified(observer)
    }
}
