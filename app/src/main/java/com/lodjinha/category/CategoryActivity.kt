package com.lodjinha.category

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.lodjinha.R
import com.lodjinha.base.BaseActivity
import com.lodjinha.base.BaseViewModel
import com.lodjinha.databinding.ActivityCategoryBinding
import com.lodjinha.home.ProductsAdapter
import com.lodjinha.model.Category
import com.lodjinha.utils.EndlessRecyclerViewScrollListener
import org.koin.android.viewmodel.ext.android.viewModel
import androidx.recyclerview.widget.DividerItemDecoration

class CategoryActivity : BaseActivity() {

    private val viewModel: CategoryViewModel by viewModel()

    companion object {
        const val CATEGORY_EXTRA = "categoryIdExtra"
    }

    override fun getViewModel(): BaseViewModel = viewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityCategoryBinding = DataBindingUtil.setContentView(this, R.layout.activity_category)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        val category = Gson().fromJson(intent.getStringExtra(CATEGORY_EXTRA), Category::class.java)
        initViews(binding, category.description)
        with(binding.viewModel!!) {
            configCategory(category.id)
            getProducts()
        }
    }

    private fun initViews(binding: ActivityCategoryBinding, categoryDescription: String) {
        configToolbar(binding, categoryDescription)
        configRecyclerViewProducts(binding)
    }

    private fun configToolbar(binding: ActivityCategoryBinding, categoryDescription: String) {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = categoryDescription
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }

    private fun configRecyclerViewProducts(binding: ActivityCategoryBinding) = with(binding.recyclerViewProducts) {
        adapter = ProductsAdapter()
        setHasFixedSize(true)
        val linearLayoutManager = LinearLayoutManager(this@CategoryActivity)
        addItemDecoration(DividerItemDecoration(this@CategoryActivity, linearLayoutManager.orientation))
        layoutManager = linearLayoutManager
        addOnScrollListener(object : EndlessRecyclerViewScrollListener(linearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                binding.viewModel!!.getProducts(page = page)
            }
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
