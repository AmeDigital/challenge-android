package com.example.domain.usecases.category

import com.example.domain.models.Category
import com.example.domain.usecases.UseCase

interface CategoryList : UseCase.FromObservable.WithoutParameter<List<Category>>