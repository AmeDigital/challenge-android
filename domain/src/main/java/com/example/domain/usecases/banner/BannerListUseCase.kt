package com.example.domain.usecases.banner

import com.example.domain.models.Banner
import com.example.domain.repositories.banner.IBannerListRepository
import io.reactivex.Observable

class BannerListUseCase(val repository: IBannerListRepository) : BannerList {
    override fun execute(): Observable<List<Banner>> {
        return repository.getBannerList()
    }
}