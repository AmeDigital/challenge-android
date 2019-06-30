package com.example.data.repositories.banner

import com.example.data.models.mapper.BannerMapper
import com.example.data.remote.Api
import com.example.domain.models.Banner
import com.example.domain.repositories.banner.IBannerListRepository
import io.reactivex.Observable

class BannerListRepository(val api: Api) : IBannerListRepository {
    override fun getBannerList(): Observable<List<Banner>> {
        return api.getBannerList().map{
            BannerMapper.mapFrom(it.results)
        }
    }
}