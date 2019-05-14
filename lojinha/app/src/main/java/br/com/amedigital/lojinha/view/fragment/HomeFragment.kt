package br.com.amedigital.lojinha.view.fragment


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.view.View
import br.com.amedigital.lojinha.R
import br.com.amedigital.lojinha.extension.setDefaultHorozontalSettings
import br.com.amedigital.lojinha.extension.setDefaultVerticalSettings
import br.com.amedigital.lojinha.model.Categoria
import br.com.amedigital.lojinha.model.CategoriaResponse
import br.com.amedigital.lojinha.model.MaisVendidoResponse
import br.com.amedigital.lojinha.model.ProdutoResponse
import br.com.amedigital.lojinha.view.activity.ProductDetailListActivity
import br.com.amedigital.lojinha.view.adapter.BestSellersAdapter
import br.com.amedigital.lojinha.view.adapter.CategoryAdapter
import br.com.amedigital.lojinha.view.adapter.MyPagerAdapter
import br.com.amedigital.lojinha.viewmodel.HomeViewModel
import com.br.cinesky.base.BaseFragment
import com.br.cinesky.model.BannerResponse
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment(), CategoryAdapter.Callbacks {

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

        val homeObservable = Observer<BannerResponse> {
            pagerAdapterView = MyPagerAdapter(it as BannerResponse, this)
            vpComponent.adapter = pagerAdapterView
            vpIndicator.setViewPager(vpComponent)
            linViewPager.visibility = View.VISIBLE
        }

        val categoryObservable = Observer<CategoriaResponse> {
            categoryAdapter = CategoryAdapter(it as CategoriaResponse, this)
            recyclerCategories.setDefaultHorozontalSettings(contextHome, categoryAdapter)
            viewLineOne.visibility = View.VISIBLE
            viewLineTwo.visibility = View.VISIBLE
            title.text = getString(R.string.categoria)
        }

        val bestSellersObservable = Observer<MaisVendidoResponse> {
            bestSellerAdapter = BestSellersAdapter(it as MaisVendidoResponse, this)
            recyclerMaisVendidos.setDefaultVerticalSettings(contextHome, bestSellerAdapter)
            tvMaisVendidos.text = getString(R.string.maisvendidos)
        }

        viewModel.bannerRepositoryResponse().observe(this, homeObservable)
        viewModel.loadRepositories()

        viewModel.categoryRepositoryResponse().observe(this, categoryObservable)
        viewModel.loadCategorys()

        viewModel.bestSellerRepositoryResponse().observe(this, bestSellersObservable)
        viewModel.loadBestSelers()

    }

    override fun open(categoria: Categoria, position: Int) {
        val intent = ProductDetailListActivity.getIntent(contextHome, categoria)
        activity!!.startActivity(intent)
    }




}
