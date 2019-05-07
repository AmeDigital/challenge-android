package br.com.sf.lojinha.ui.main

import br.com.sf.lojinha.injection.PerActivity
import javax.inject.Inject

@PerActivity
internal class MainPresenter @Inject
constructor(private var view: MainContract.View, private var navigator: MainContract.Navigator) :
    MainContract.Presenter {

    override fun clickHome() {
        view.highlightHome()
        navigator.goToHome()
    }

    override fun clickAbout() {
        view.highlightAbout()
        navigator.goToAbout()
    }

    override fun destroy() {
    }
}
