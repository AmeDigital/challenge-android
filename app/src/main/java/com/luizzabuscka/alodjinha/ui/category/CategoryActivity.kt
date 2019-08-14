package com.luizzabuscka.alodjinha.ui.category

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.*
import androidx.paging.PagedList
import com.luizzabuscka.alodjinha.R
import com.luizzabuscka.alodjinha.components.adapters.ProductsPagedAdapter
import com.luizzabuscka.commons.model.Category
import com.luizzabuscka.commons.model.Product
import com.luizzabuscka.commons.model.State
import com.luizzabuscka.viewmodel.CategoryViewModel
import kotlinx.android.synthetic.main.activity_category.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.indeterminateProgressDialog


class CategoryActivity : AppCompatActivity(), LifecycleOwner {

    private lateinit var adapter: ProductsPagedAdapter
    private lateinit var category: Category

    private val progressDialog by lazy {
        indeterminateProgressDialog(
            getString(R.string.dialog_please_wait),
            getString(R.string.dialog_loading)
        )
    }

    private var viewModel: CategoryViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        progressDialog.setCancelable(false)

        category = intent?.extras?.get("category") as Category

        viewModel = CategoryViewModel(category.id)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = category.description

        configProducts()
        initState()
    }

    private fun configProducts() {
        adapter = ProductsPagedAdapter { viewModel?.retry() }
        rvProducts.adapter = adapter
        viewModel?.products?.observe(this, Observer<PagedList<Product>> {
            if (progressDialog.isShowing) {
                progressDialog.hide()
            }
            adapter.submitList(it)
        })
    }

    private fun initState() {
        viewModel?.getState()?.observe(this, Observer { state ->
            if (viewModel?.listIsEmpty()!! && state == State.LOADING) {
                progressDialog.show()
            }

            if (viewModel?.listIsEmpty()!! && state == State.ERROR) {
                alert(getString(R.string.dialog_fail_load_data)) {
                    this.isCancelable = false
                    positiveButton(getString(R.string.dialog_button_try_again)) {
                        initState()
                    }
                }.show()
            }

            if (!viewModel?.listIsEmpty()!!) {
                progressDialog.hide()
                adapter.setState(state ?: State.DONE)
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    private val lifecycleRegistry = LifecycleRegistry(this)
    override fun getLifecycle(): Lifecycle {
        return lifecycleRegistry
    }
}
