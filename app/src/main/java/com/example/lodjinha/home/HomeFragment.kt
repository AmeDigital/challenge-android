package com.example.lodjinha.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.Banner
import com.example.domain.models.Product
import com.example.domain.models.Category
import com.example.lodjinha.MainActivity
import com.example.lodjinha.product.ProductFragment
import com.example.lodjinha.productList.ProductListFragment
import com.example.lodjinha.R
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class HomeFragment : Fragment(), HomeContract.View {
    private val presenter by inject<HomeContract.Presenter> { parametersOf(this) }

    companion object {
        fun newInstance(): HomeFragment {
            val fragment = HomeFragment()

            return fragment
        }
    }

    fun onBestSellerItemClicked(productId: Int) {
        val fragment = ProductFragment.newInstance(productId)
        (activity as MainActivity).addFragment(fragment, true)
    }

    fun onCategoryItemClicked(categoryId: Int) {
        val fragment = ProductListFragment.newInstance(categoryId)
        (activity as MainActivity).addFragment(fragment, true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvCategory?.let {
            it.adapter = CategoryAdapter { categoryId ->
                onCategoryItemClicked(categoryId)
            }
            it.layoutManager =  LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        }

        rvBestSellers?.let {
            it.adapter = BestSellerAdapter { productId ->
                onBestSellerItemClicked(productId)
            }
            it.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        }

        presenter.getBannerList()
        presenter.getCategoriesList()
        presenter.getBestSellersList()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun showBannerList(list: List<Banner>) {
        val bannerAdapter = BannerAdapter(requireFragmentManager(), list)

        rvBanner?.adapter = bannerAdapter
        tabDots?.setupWithViewPager(rvBanner, true)
    }

    override fun showCategoriesList(list: List<Category>) {
        (rvCategory.adapter as CategoryAdapter).populateCategories(list)
    }

    override fun showBestSellersList(list: List<Product>) {
        (rvBestSellers.adapter as BestSellerAdapter).populateBestSellers(list)
    }
}