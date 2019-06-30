package com.example.domain.usecases.product

import com.example.domain.models.Product
import com.example.domain.models.ProductInfo
import com.example.domain.usecases.UseCase

interface ProductList: UseCase.FromObservable.WithParameter<ProductInfo, List<Product>>