package com.leonardoalves.ametest.store.viewmodel

import com.leonardoalves.ametest.custom.ViewModel

class StoreBannerViewModel(val bannerList: List<BannerItemViewModel>) : ViewModel

class BannerItemViewModel(val image: String, val link: String): ViewModel