package com.example.domain.usecases.product

import com.example.domain.models.Product
import com.example.domain.usecases.UseCase

interface Product : UseCase.FromObservable.WithParameter<Int, Product>