package com.example.domain.usecases.banner

import com.example.domain.models.Banner
import com.example.domain.usecases.UseCase

interface BannerList : UseCase.FromObservable.WithoutParameter<List<Banner>>