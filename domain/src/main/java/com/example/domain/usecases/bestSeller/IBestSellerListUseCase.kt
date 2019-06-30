package com.example.domain.usecases.bestSeller

import com.example.domain.models.Product
import com.example.domain.usecases.UseCase

interface BestSellerList: UseCase.FromObservable.WithoutParameter<List<Product>>