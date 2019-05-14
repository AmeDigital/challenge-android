package br.com.amedigital.lojinha.view


import android.view.View
import br.com.amedigital.lojinha.R
import com.br.cinesky.base.BaseFragment

class AboutFragment : BaseFragment() {

    companion object {
        fun newInstance() = AboutFragment()
    }

    override fun getRootLayoutId(): Int {
        return R.layout.fragment_about
    }

    override fun setupViewModel() {
    }

    override fun setupData(view: View) {
    }


}
