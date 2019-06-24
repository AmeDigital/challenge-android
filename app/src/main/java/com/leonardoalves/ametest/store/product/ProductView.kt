package com.leonardoalves.ametest.store.product

import com.leonardoalves.ametest.store.viewmodel.StoreProductViewModel

interface ProductView {
    fun fillProductDetails(viewModel: StoreProductViewModel)
    fun showReservationSuccessMessage()
    fun showReservationErrorMessage()
    fun showErrorCriticalMessage()
}