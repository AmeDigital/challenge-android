package marco.challenge_android.presenter.home

class MainPresenter(private val view: MainContract.View) : MainContract.Presenter {

    override fun initHomeFragment() {
        view.initHomeFragment()
    }

    override fun subscribe() {
        this.initHomeFragment()
    }

}