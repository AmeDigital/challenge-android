package com.example.domain.repositories.banner

import com.example.domain.models.Banner
import io.reactivex.Observable

interface IBannerListRepository {
    fun getBannerList(): Observable<List<Banner>>
}