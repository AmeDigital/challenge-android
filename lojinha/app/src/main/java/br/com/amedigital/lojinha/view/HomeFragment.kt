package br.com.amedigital.lojinha.view


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import br.com.amedigital.lojinha.R
import br.com.amedigital.lojinha.model.Banner
import br.com.amedigital.lojinha.model.CategoriaResponse
import br.com.amedigital.lojinha.model.MaisVendidoResponse
import br.com.amedigital.lojinha.utils.setDefaultHorozontalSettings
import br.com.amedigital.lojinha.utils.setDefaultVerticalSettings
import br.com.amedigital.lojinha.view.adapter.BestSellersAdapter
import br.com.amedigital.lojinha.view.adapter.CategoryAdapter
import br.com.amedigital.lojinha.view.adapter.MyPagerAdapter
import br.com.amedigital.lojinha.viewmodel.HomeViewModel
import com.br.cinesky.base.BaseFragment
import com.br.cinesky.model.BannerResponse
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment(), MyPagerAdapter.Callbacks {

    private lateinit var contextHome: Context
    private lateinit var viewModel: HomeViewModel
    private lateinit var pagerAdapterView: MyPagerAdapter
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var bestSellerAdapter: BestSellersAdapter

    companion object {
        fun newInstance() = HomeFragment()
    }

    override fun getRootLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun setupViewModel() {
        if (!this::viewModel.isInitialized) {
            viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
            setObserveLive(viewModel)
        }
    }

    override fun setupData(view: View) {
        contextHome = view.context
        title.text = getString(R.string.categoria)
        tvMaisVendidos.text = getString(R.string.maisvendidos)

        val homeObservable = Observer<BannerResponse> {
            pagerAdapterView = MyPagerAdapter(it as BannerResponse, this)
            vpComponent.adapter = pagerAdapterView
            vpIndicator.setViewPager(vpComponent)
        }

        val categoryObservable = Observer<CategoriaResponse> {
            categoryAdapter = CategoryAdapter(it as CategoriaResponse, this)
            recyclerCategories.setDefaultHorozontalSettings(contextHome,categoryAdapter)
        }

        val bestSellersObservable = Observer<MaisVendidoResponse> {
            bestSellerAdapter = BestSellersAdapter(it as MaisVendidoResponse, this)
            recyclerMaisVendidos.setDefaultVerticalSettings(contextHome,bestSellerAdapter)
        }

        viewModel.bannerRepositoryResponse().observe(this, homeObservable)
        viewModel.loadRepositories()

        viewModel.categoryRepositoryResponse().observe(this, categoryObservable)
        viewModel.loadCategorys()

        viewModel.bestSellerRepositoryResponse().observe(this, bestSellersObservable)
        viewModel.loadBestSelers()

    }

    private fun setupCarousel(it: MutableList<Banner>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun open(movie: Banner, position: Int) {

    }
}
