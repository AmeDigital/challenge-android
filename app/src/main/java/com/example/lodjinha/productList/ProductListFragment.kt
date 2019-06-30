package com.example.lodjinha.productList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.Product
import com.example.domain.models.ProductInfo
import com.example.lodjinha.MainActivity
import com.example.lodjinha.R
import com.example.lodjinha.product.ProductFragment
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.fragment_list_products.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class ProductListFragment() : Fragment(), ProductListContract.View {
    private val presenter by inject<ProductListContract.Presenter> { parametersOf(this) }
    private var currentPage = 0

    companion object {
        private const val CATEGORY_ID_OBJECT = "categoryIdObject"

        fun newInstance(categoryId: Int) = ProductListFragment().apply {
            arguments = Bundle().apply {
                putInt(CATEGORY_ID_OBJECT, categoryId)
            }
        }
    }

    fun onProductItemClicked(productId: Int) {
        val fragment = ProductFragment.newInstance(productId)
        (activity as MainActivity).addFragment(fragment, true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            val categoryId = it[CATEGORY_ID_OBJECT] as Int
            presenter.getProductsList(ProductInfo(categoryId, 0, 20))
        }

        rvProductList?.let {
            it.setHasFixedSize(true)
            val layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            it.layoutManager =  layoutManager
            it.clearOnScrollListeners()
            it.addOnScrollListener(InfiniteScrollListener({
                requestProducts()
            }, layoutManager))
            it.adapter = ProductsListAdapter { productId ->
                onProductItemClicked(productId)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as MainActivity).getSupportActionBar()?.show()

        val toolbar = (activity as MainActivity).toolbar
        toolbar?.setNavigationIcon(R.drawable.ic_arrow_back_white)
        toolbar?.setTitleTextAppearance(context, R.style.CategoryToolbar)
        toolbar?.setLogo(null)
        toolbar?.setNavigationOnClickListener {
            (activity as MainActivity).setMainToolbar()
            fragmentManager?.popBackStack()
        }

        return inflater.inflate(R.layout.fragment_list_products, container, false)
    }

    override fun showProductsList(list: List<Product>) {
        (rvProductList.adapter as ProductsListAdapter).populateProductList(list)
    }

    private fun requestProducts() {
        currentPage += 7
        arguments?.let {
            val categoryId = it[CATEGORY_ID_OBJECT] as Int
            presenter.getProductsList(ProductInfo(categoryId, currentPage, 20))
        }
    }

    override fun showLoading() {
        pbProductList?.visibility = VISIBLE
    }

    override fun hideLoading() {
        pbProductList?.visibility = GONE
    }
}