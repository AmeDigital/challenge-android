package com.leonardoalves.ametest.store

import android.util.Log
import com.leonardoalves.repository.repository.StoreRepository
import io.reactivex.disposables.CompositeDisposable

class StorePresenter(private val repository: StoreRepository) {

    private val compositeDisposable = CompositeDisposable()

    fun onCreate(){
        compositeDisposable.add(
            repository.getBanners()
                .subscribe ({
                    Log.e("RESPONSE", it.size.toString())
                },{it.printStackTrace()},{})
        )
    }

    fun onDestroy(){
        compositeDisposable.clear()
    }
}