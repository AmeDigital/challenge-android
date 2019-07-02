package com.lodjinha.detailProduct

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.google.gson.Gson
import com.lodjinha.R
import com.lodjinha.base.BaseActivity
import com.lodjinha.base.BaseViewModel
import com.lodjinha.databinding.ActivityDetailProductBinding
import com.lodjinha.model.Product
import org.koin.android.viewmodel.ext.android.viewModel

class DetailProductActivity : BaseActivity() {

    private val viewModel: DetailProductViewModel by viewModel()

    companion object {
        const val DETAIL_PRODUCT_EXTRA = "detailProductExtra"
    }

    override fun getViewModel(): BaseViewModel = viewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityDetailProductBinding= DataBindingUtil.setContentView(this, R.layout.activity_detail_product)
        val product = Gson().fromJson(intent.getStringExtra(DETAIL_PRODUCT_EXTRA), Product::class.java)
        binding.viewModel = viewModel
        binding.viewModel!!.configProduct(product)
        binding.lifecycleOwner = this
        initViews(binding, product)
    }

    private fun initViews(binding: ActivityDetailProductBinding, product: Product) {
        configToolbar(binding, product.category.description)
    }

    private fun configToolbar(binding: ActivityDetailProductBinding, categoryDescription: String) {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = categoryDescription
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
