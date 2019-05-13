package com.wagnermessias.olodjinha.feature.products.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.wagnermessias.olodjinha.core.interactor.ReservationProductInteractor
import com.wagnermessias.olodjinha.core.model.ReservationResponse
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers.Unconfined
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import retrofit2.Response
import java.io.IOException

class ProductDetailViewModelTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val interactorResevationProductMock = mockk<ReservationProductInteractor>()
    private val viewModel = ProductDetailViewModel(interactorResevationProductMock, Unconfined)

    @Test
    fun reservationResponseResultSuccess_ReservationProducts_ExposeReservationProductState() {
        val reservationResponseMock = mockk<ReservationResponse>()

        val expectedReservationProductState = ProductDetailViewState.ReservationProduct(reservationResponseMock)
        val responseMock = mockk<Response<ReservationResponse>>()


        coEvery { interactorResevationProductMock.execute(any()) } returns responseMock
        every { responseMock.isSuccessful } returns true
        every { responseMock.body() } returns reservationResponseMock

        every { reservationResponseMock.result} returns "success"


        viewModel.reservationProducts(1)

        assertEquals(viewModel.detailViewState.value, expectedReservationProductState)
    }

    @Test
    fun reservationResponseResultError_ReservationProducts_ExposeReservationErrorState() {
        val reservationResponseMock = mockk<ReservationResponse>()
        val message = "Produto não encontrado";
        val expectedReservationErrorState = ProductDetailViewState.ReservationError(message)
        val responseMock = mockk<Response<ReservationResponse>>()
        coEvery { interactorResevationProductMock.execute(any()) } returns responseMock
        every { responseMock.isSuccessful } returns true
        every { responseMock.body() } returns reservationResponseMock
        every { reservationResponseMock.result} returns "error"
        every { reservationResponseMock.mensagem} returns message

        viewModel.reservationProducts(23445)

        assertEquals(viewModel.detailViewState.value, expectedReservationErrorState)
    }

    @Test
    fun responseSuccessfulFalse_ReservationProducts_ExposeServerErrorState() {
        val expectedServerErrorState = ProductDetailViewState.ServerError
        val responseMock = mockk<Response<ReservationResponse>>()
        coEvery { interactorResevationProductMock.execute(any()) } returns responseMock
        every { responseMock.isSuccessful } returns false

        viewModel.reservationProducts(1)
        assertEquals(viewModel.detailViewState.value, expectedServerErrorState)
    }

    @Test
    fun networkException_ReservationProducts_ExposeNetworkErrorState() {
        val expectedNetworkErrorState = ProductDetailViewState.NetworkError
        val responseMock = mockk<Response<ReservationResponse>>()
        coEvery { interactorResevationProductMock.execute(any()) } throws IOException()

        viewModel.reservationProducts(1)
        assertEquals(viewModel.detailViewState.value, expectedNetworkErrorState)
    }
}