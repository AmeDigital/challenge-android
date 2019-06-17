package marco.challenge_android.presenter

interface BaseView<T> {
    var presenter: T
    fun initUI()
    fun addListeners()

}