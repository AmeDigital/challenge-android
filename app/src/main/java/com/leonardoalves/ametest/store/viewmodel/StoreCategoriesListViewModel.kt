package com.leonardoalves.ametest.store.viewmodel

import com.leonardoalves.ametest.custom.ViewModel

class StoreCategoriesListViewModel(val categories: List<StoreCategoryViewModel>): ViewModel

class StoreCategoryViewModel(val id: Int = 0,
                             val image: String,
                             val description: String):ViewModel