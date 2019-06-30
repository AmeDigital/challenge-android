package com.example.domain.usecases.product

import com.example.domain.usecases.UseCase

interface ReserveUseCase : UseCase.FromCompletable.WithParameter<Int>
