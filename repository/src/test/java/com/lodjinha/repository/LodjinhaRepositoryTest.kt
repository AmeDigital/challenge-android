package com.lodjinha.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.lodjinha.common_teste.rules.CoroutinesMainDispatcherRule
import com.lodjinha.model.Banners
import com.lodjinha.model.Categories
import com.lodjinha.model.Product
import com.lodjinha.model.Products
import com.lodjinha.remote.LodjinhaDataSource
import com.lodjinha.repository.utils.FakeData
import com.lodjinha.repository.utils.Resource
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verifyOrder
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class LodjinhaRepositoryTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutinesMainDispatcherRule = CoroutinesMainDispatcherRule()

    private lateinit var observerBanners: Observer<Resource<Banners>>
    private lateinit var observerCategories: Observer<Resource<Categories>>
    private lateinit var observerProducts: Observer<Resource<Products>>
    private lateinit var observerProduct: Observer<Resource<Product>>
    private val productId = 1

    private lateinit var repository: LodjinhaRepository
    private val remoteDataSource = mockk<LodjinhaDataSource>()

    private val exception = Exception("Internet")

    @Before
    fun setUp() {
        observerBanners = mockk(relaxed = true)
        observerCategories = mockk(relaxed = true)
        observerProducts = mockk(relaxed = true)
        observerProduct = mockk(relaxed = true)
        repository = LodjinhaRepositoryImpl(dataSource = remoteDataSource)
    }

    @Test
    fun `Get banners from network`() {
        val fakeBanners = FakeData.createFakeBanners()
        every {
            remoteDataSource.getBannerAsync()
        } returns GlobalScope.async { fakeBanners }

        runBlocking {
            repository.getBannerAsync().observeForever(observerBanners)
        }

        verifyOrder {
            observerBanners.onChanged(Resource.loading(null)) // Loading from remote source
            observerBanners.onChanged(Resource.success(fakeBanners)) // Success
        }
        confirmVerified(observerBanners)
    }

    @Test
    fun `Get banners from network when no internet is available`() {
        every {
            remoteDataSource.getBannerAsync()
        } throws exception
        runBlocking {
            repository.getBannerAsync().observeForever(observerBanners)
        }

        verifyOrder {
            observerBanners.onChanged(Resource.loading(null)) // Init loading with no value
        }
        confirmVerified(observerBanners)
    }

    @Test
    fun `Get categories from network`() {
        val fakeCategories = FakeData.createFakeCategories()
        every {
            remoteDataSource.getCategoriesAsync()
        } returns GlobalScope.async { fakeCategories }

        runBlocking {
            repository.getCategoriesAsync().observeForever(observerCategories)
        }

        verifyOrder {
            observerCategories.onChanged(Resource.loading(null))
            observerCategories.onChanged(Resource.success(fakeCategories))
        }
        confirmVerified(observerCategories)
    }

    @Test
    fun `Get categories from network when no internet is available`() {
        val exception = Exception("Internet")
        every {
            remoteDataSource.getCategoriesAsync()
        } throws exception

        runBlocking {
            repository.getCategoriesAsync().observeForever(observerCategories)
        }

        verifyOrder {
            observerCategories.onChanged(Resource.loading(null))
        }
        confirmVerified(observerCategories)
    }

    @Test
    fun `Get products from network`() {
        val fakeProducts = FakeData.createFakeProducts()
        every {
            remoteDataSource.getProductsAsync()
        } returns GlobalScope.async { fakeProducts }

        runBlocking {
            repository.getProductsAsync().observeForever(observerProducts)
        }

        verifyOrder {
            observerProducts.onChanged(Resource.loading(null))
            observerProducts.onChanged(Resource.success(fakeProducts))
        }
        confirmVerified(observerProducts)
    }

    @Test
    fun `Get products from network when no internet is available`() {
        val exception = Exception("Internet")
        every {
            remoteDataSource.getProductsAsync()
        } throws exception

        runBlocking {
            repository.getProductsAsync().observeForever(observerProducts)
        }

        verifyOrder {
            observerProducts.onChanged(Resource.loading(null))
        }
        confirmVerified(observerProducts)
    }

    @Test
    fun `Get top selling products from network`() {
        val fakeProducts = FakeData.createFakeProducts()
        every {
            remoteDataSource.getTopSellingProductsAsync()
        } returns GlobalScope.async { fakeProducts }

        runBlocking {
            repository.getTopSellingProductsAsync().observeForever(observerProducts)
        }

        verifyOrder {
            observerProducts.onChanged(Resource.loading(null))
            observerProducts.onChanged(Resource.success(fakeProducts))
        }
        confirmVerified(observerProducts)
    }

    @Test
    fun `Get top selling products from network when no internet is available`() {
        val exception = Exception("Internet")
        every {
            remoteDataSource.getTopSellingProductsAsync()
        } throws exception

        runBlocking {
            repository.getTopSellingProductsAsync().observeForever(observerProducts)
        }

        verifyOrder {
            observerProducts.onChanged(Resource.loading(null))
        }
        confirmVerified(observerProducts)
    }

    @Test
    fun `Get product from network`() {
        val fakeProduct = FakeData.createFakeProducts().data[0]
        every {
            remoteDataSource.getProductAsync(productId)
        } returns GlobalScope.async { fakeProduct }

        runBlocking {
            repository.getProductAsync(productId).observeForever(observerProduct)
        }

        verifyOrder {
            observerProduct.onChanged(Resource.loading(null))
            observerProduct.onChanged(Resource.success(fakeProduct))
        }
        confirmVerified(observerProduct)
    }

    @Test
    fun `Get product from network when no internet is available`() {
        val exception = Exception("Internet")
        every {
            remoteDataSource.getProductAsync(productId)
        } throws exception

        runBlocking {
            repository.getProductAsync(productId).observeForever(observerProduct)
        }

        verifyOrder {
            observerProduct.onChanged(Resource.loading(null))
        }
        confirmVerified(observerProduct)
    }
}
