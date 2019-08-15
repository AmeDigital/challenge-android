package com.luizzabuscka.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.luizzabuscka.commons.model.Banner
import com.luizzabuscka.commons.model.Category
import com.luizzabuscka.commons.model.Product
import com.luizzabuscka.data.webservice.WebService
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Test
import org.junit.Before
import org.junit.Rule
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class HomeViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var service: WebService
    lateinit var viewModel: HomeViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = HomeViewModel()
    }

    @Test
    fun getBanners() {
        whenever(service.getBanners()).thenReturn(Single.create(mock()))
        val mockObserver: Observer<List<Banner>> = mock()
        viewModel.getBanners().observeForever(mockObserver)
    }

    @Test
    fun getCategories() {
        whenever(service.getCategories()).thenReturn(Single.create(mock()))
        val mockObserver: Observer<List<Category>> = mock()
        viewModel.getCategories().observeForever(mockObserver)
    }

    @Test
    fun getBestSellers() {
        whenever(service.getBestSellers()).thenReturn(Single.create(mock()))
        val mockObserver: Observer<List<Product>> = mock()
        viewModel.getBestSellers().observeForever(mockObserver)
    }
}