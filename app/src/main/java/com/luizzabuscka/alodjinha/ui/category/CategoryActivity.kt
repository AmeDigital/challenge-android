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
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.getViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import java.lang.System.setProperty


class CategoryActivity : AppCompatActivity(), LifecycleOwner {

    private lateinit var adapter: ProductsPagedAdapter

    private val category: Category by lazy {
        intent?.extras?.get("category") as Category
    }

    private val progressDialog by lazy {
        indeterminateProgressDialog(
            getString(R.string.dialog_please_wait),
            getString(R.string.dialog_loading)
        )
    }

    private val categoryViewModel: CategoryViewModel by lazy {
        CategoryViewModel(category.id)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        progressDialog.setCancelable(false)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = category.description

        configProducts()
        initState()
    }

    private fun configProducts() {
        adapter = ProductsPagedAdapter { categoryViewModel.retry() }
        rvProducts.adapter = adapter
        categoryViewModel.products.observe(this, Observer<PagedList<Product>> {
            if (progressDialog.isShowing) {
                progressDialog.hide()
            }
            adapter.submitList(it)
        })
    }

    private fun initState() {
        categoryViewModel.getState().observe(this, Observer { state ->
            if (categoryViewModel.listIsEmpty() && state == State.LOADING) {
                progressDialog.show()
            }

            if (categoryViewModel.listIsEmpty() && state == State.ERROR) {
                alert(getString(R.string.dialog_fail_load_data)) {
                    this.isCancelable = false
                    positiveButton(getString(R.string.dialog_button_try_again)) {
                        initState()
                    }
                }.show()
            }

            if (!categoryViewModel.listIsEmpty()) {
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
