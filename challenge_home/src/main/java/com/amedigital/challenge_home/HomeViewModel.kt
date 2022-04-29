package com.amedigital.challenge_home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amedigital.challenge_model.Banner
import com.amedigital.challenge_model.api.Resource
import com.amedigital.challenge_model.repositories.BannerRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val bannerRepository: BannerRepository) : ViewModel() {
    private val _banner: MutableLiveData<Resource<List<Banner>>> = MutableLiveData()
    val banner: LiveData<Resource<List<Banner>>>
        get() = _banner

    init {
        loadBanner()
    }

    fun loadBanner() = viewModelScope.launch {
        _banner.value = Resource.Requesting
        _banner.value = bannerRepository.getBanner()
    }
}