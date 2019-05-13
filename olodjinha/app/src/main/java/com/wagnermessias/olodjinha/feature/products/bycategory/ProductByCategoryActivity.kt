package com.wagnermessias.olodjinha.feature.products.bycategory

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wagnermessias.olodjinha.R
import com.wagnermessias.olodjinha.core.base.BaseActivity
import com.wagnermessias.olodjinha.core.extensions.OnItemClickListener
import com.wagnermessias.olodjinha.core.extensions.addOnItemClickListener
import com.wagnermessias.olodjinha.core.model.Category
import com.wagnermessias.olodjinha.core.model.Product
import com.wagnermessias.olodjinha.feature.products.ProductsAdapter
import com.wagnermessias.olodjinha.feature.products.detail.ProductDetailActivity
import kotlinx.android.synthetic.main.content_product_by_category.*
import kotlinx.android.synthetic.main.product_by_category_activity.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductByCategoryActivity : BaseActivity() {

    private val firstOffset = 0
    private var categoryId: Int = 0
    private var productsList: ArrayList<Product> = ArrayList()
    private lateinit var linearLayoutManager: LinearLayoutManager
    private val lastVisibleItemPosition: Int get() = linearLayoutManager.findLastVisibleItemPosition()
    private lateinit var scrollListener: RecyclerView.OnScrollListener

    private val productsByCategoryViewModel: ProductsByCategoryViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.product_by_category_activity)
        setupViews()
        observeViewModel()
        initListeners()
        initProductsByCategory(intent.extras)
    }

    private fun setupViews() {
        rv_products_by_category.layoutManager = LinearLayoutManager(this)
        linearLayoutManager = (rv_products_by_category.layoutManager as LinearLayoutManager?)!!
        setRecyclerViewScrollListener()
    }

    private fun observeViewModel() {
        productsByCategoryViewModel.byCategoryViewState.observe(this, Observer {
            when (it) {
                is ProductsByCategoryViewState.ProductsByCategoryList -> setProducts(it.products)
                is ProductsByCategoryViewState.NetworkError -> showErrorAndTryAgain(R.string.alert_error_network)
                is ProductsByCategoryViewState.ServerError -> showErrorAndTryAgain(R.string.alert_error_server)
                is ProductsByCategoryViewState.EmptyList -> showEmptyList()
            }
        })
    }

    private fun showEmptyList() {
        text_no_product_found.visibility = View.VISIBLE
    }

    private fun initProductsByCategory(bundle: Bundle) {
        val category = bundle.getSerializable(CATEGORY_INFO) as Category
        category.apply {
            categoryId = this.id
            title_category.text = this.description
            productsByCategoryViewModel.loadProductsByCategory(this.id, firstOffset)
        }
    }

    private fun initListeners() {
        rv_products_by_category.addOnItemClickListener(object : OnItemClickListener {
            override fun onItemClicked(position: Int, view: View) {
                startActivity(ProductDetailActivity.newInstance(this@ProductByCategoryActivity, productsList[position]))
            }
        })

        button_try_again.setOnClickListener {
            it.visibility = View.GONE
            productsByCategoryViewModel.loadProductsByCategory(categoryId, firstOffset)
        }

        toolbar_category.setNavigationOnClickListener {
            finish()
        }
    }

    private fun setProducts(products: ArrayList<Product>) {
        productsList = products
        showProgress(false)
        if (linearLayoutManager.findLastVisibleItemPosition() > 0) {
            rv_products_by_category.adapter?.notifyDataSetChanged()
        } else {
            rv_products_by_category.adapter =
                ProductsAdapter(this.productsList, this)
        }
    }

    private fun setRecyclerViewScrollListener() {
        scrollListener = object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val totalItemCount = recyclerView.layoutManager?.itemCount
                if (totalItemCount == lastVisibleItemPosition + 1) {
                    showProgress(true)
                    productsByCategoryViewModel.loadProductsByCategory(categoryId, totalItemCount)
                    //rv_products_by_category.removeOnScrollListener(scrollListener)
                }
            }
        }
        rv_products_by_category.addOnScrollListener(scrollListener)
    }

    private fun showProgress(show: Boolean) {
        progress_category.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun showErrorAndTryAgain(idMenssage: Int) {
        showAlertDialog(idMenssage)
        button_try_again.visibility = View.VISIBLE
    }

    companion object {
        private const val CATEGORY_INFO = "category_param"

        fun newInstance(
            context: Context,
            category: Category
        ) = Intent(context, ProductByCategoryActivity::class.java).apply {
            Bundle().apply {
                putSerializable(CATEGORY_INFO, category)
                putExtras(this)
            }
        }
    }

}
