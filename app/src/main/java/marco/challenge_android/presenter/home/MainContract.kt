package marco.challenge_android.presenter.home

import android.support.v4.app.Fragment
import marco.challenge_android.presenter.BasePresenter
import marco.challenge_android.presenter.BaseView

interface MainContract {

    interface View : BaseView<Presenter> {
        fun initHomeFragment()
        fun changeFragment(fragment: Fragment)
    }

    interface Presenter : BasePresenter {
        fun initHomeFragment()
    }

}