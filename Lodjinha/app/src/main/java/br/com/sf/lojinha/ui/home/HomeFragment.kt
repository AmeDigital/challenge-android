package br.com.sf.lojinha.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.sf.lojinha.R
import br.com.sf.lojinha.injection.PerFragment
import br.com.sf.lojinha.repository.Category
import br.com.sf.lojinha.repository.Product
import br.com.sf.lojinha.ui.base.DaggerFragment
import br.com.sf.lojinha.ui.main.MainActivity
import br.com.sf.lojinha.ui.products_list.ProductsAdapter
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.Unbinder
import ss.com.bannerslider.Slider
import javax.inject.Inject


@PerFragment
class HomeFragment : DaggerFragment(), HomeContract.View {

    @BindView(R.id.toolbar)
    internal lateinit var toolbar: Toolbar

    @BindView(R.id.carouselView)
    internal lateinit var slider: Slider

    @BindView(R.id.listCategories)
    lateinit var listCategories: RecyclerView

    @BindView(R.id.listBestSellers)
    lateinit var listBestSellers: RecyclerView

    @BindView(R.id.pbCategories)
    lateinit var pbCategories: ProgressBar

    @BindView(R.id.pbBestSellers)
    lateinit var pbBestSellers: ProgressBar

    @Inject
    internal lateinit var presenter: HomeContract.Presenter

    private var unbinder: Unbinder? = null

    private lateinit var categoryAdapter: CategoryAdapter

    private lateinit var bestSellersAdapter: ProductsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        unbinder = ButterKnife.bind(this, view)

        categoryAdapter = CategoryAdapter(presenter::clickCategory)
        bestSellersAdapter = ProductsAdapter(presenter::clickProduct)

        listCategories.setHasFixedSize(true)
        listCategories.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        listCategories.adapter = categoryAdapter

        listBestSellers.setHasFixedSize(true)
        listBestSellers.layoutManager = LinearLayoutManager(activity)
        listBestSellers.adapter = bestSellersAdapter

        toolbar.setNavigationOnClickListener {
            (activity as MainActivity).toggleDrawer()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupAppBar()
        presenter.start()
    }

    private fun setupAppBar() {
        (activity as MainActivity)
            .customAppBar
            .apply {
                setTitle(getString(R.string.application_name))
                clearMenu()
                setMenuRes(R.menu.menu_empty, R.menu.menu_empty, R.menu.menu_empty)
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        unbinder?.unbind()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.destroy()
    }

    override fun showBestSellers(products: List<Product>) {
        bestSellersAdapter.addProducts(products)
    }

    override fun showCategories(categories: List<Category>) {
        categoryAdapter.addCategories(categories)
    }

    override fun showBanners(banners: List<String>) {
        slider.setAdapter(HomeSliderAdapter(banners))
    }

    override fun showBestSellersLoading(show: Boolean) {
        if (show) {
            pbBestSellers.visibility = View.VISIBLE
        } else {
            pbBestSellers.visibility = View.GONE
        }
    }

    override fun showCategoriesLoading(show: Boolean) {
        if (show) {
            pbCategories.visibility = View.VISIBLE
        } else {
            pbCategories.visibility = View.GONE
        }
    }

    companion object {
        fun newInstance(): Fragment {
            return HomeFragment()
        }
    }
}
