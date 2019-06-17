package marco.challenge_android.presenter

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

interface BasePresenter {

    private val mCompositeDisposable: CompositeDisposable
        get() = CompositeDisposable()

    fun addDisposable(disposable: Disposable) {
        mCompositeDisposable.addAll(disposable)
    }

    fun subscribe()

    fun unsubscribe() {
        mCompositeDisposable.clear()
    }

}