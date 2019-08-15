package com.luizzabuscka.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.luizzabuscka.data.webservice.WebService
import com.nhaarman.mockitokotlin2.*
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations.initMocks


class ProductViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var service: WebService

    lateinit var viewModel: ProductViewModel

    @Before
    fun setUp() {
        initMocks(this)
        viewModel = ProductViewModel()
    }

    @Test
    fun reserveProduct() {
        whenever(service.reserveProduct(1)).thenReturn(Single.create(mock()))
        val mockObserver: Observer<Any> = mock()
        viewModel.reserveProduct(1).observeForever(mockObserver)
    }
}