package com.luizzabuscka.viewmodel

import androidx.lifecycle.Observer
import com.luizzabuscka.commons.model.Product
import com.luizzabuscka.data.webservice.WebService
import com.nhaarman.mockitokotlin2.*
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.anyInt
import org.mockito.MockitoAnnotations.initMocks
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Rule


class CategoryViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var service: WebService
    lateinit var viewModel: CategoryViewModel

    @Before
    fun setUp() {
        initMocks(this)
        viewModel = CategoryViewModel(1)
    }

    @Test
    fun retry() {
        whenever(service.getProducts(anyInt(), anyInt(), anyInt())).thenReturn(Single.just(mock()))
        val mockObserver: Observer<List<Product>> = mock()
        viewModel.products.observeForever(mockObserver)
        viewModel.retry()
        verify(mockObserver).onChanged(any())
    }

    @Test
    fun listIsEmpty() {
        assertTrue(viewModel.listIsEmpty())
    }

    @Test
    fun getState() {
        val state = viewModel.getState()
        assertNotNull(state)
    }
}