package com.lodjinha.detailProduct

import android.graphics.Paint
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.google.gson.Gson
import com.lodjinha.R
import com.lodjinha.base.BaseActivity
import com.lodjinha.base.BaseViewModel
import com.lodjinha.databinding.ActivityDetailProductBinding
import com.lodjinha.model.Product
import com.lodjinha.repository.utils.Resource
import org.koin.android.viewmodel.ext.android.viewModel

class DetailProductActivity : BaseActivity() {

    private val viewModel: DetailProductViewModel by viewModel()

    companion object {
        const val DETAIL_PRODUCT_EXTRA = "detailProductExtra"
    }

    override fun getViewModel(): BaseViewModel = viewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityDetailProductBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail_product)
        val product = Gson().fromJson(intent.getStringExtra(DETAIL_PRODUCT_EXTRA), Product::class.java)
        binding.viewModel = viewModel
        binding.viewModel!!.configProduct(product)
        binding.lifecycleOwner = this
        initViews(binding, product.category.description)

        binding.viewModel!!.response.observe(this, Observer {
            if (it.status == Resource.Status.SUCCESS) {
                reserveProductSuccess()
            }
        })
    }

    private fun initViews(binding: ActivityDetailProductBinding, categoryDescription: String) {
        configToolbar(binding, categoryDescription)
        binding.priceArea.originalPriceProduct.paintFlags =
            binding.priceArea.originalPriceProduct.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
    }


    private fun configToolbar(binding: ActivityDetailProductBinding, categoryDescription: String) {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = categoryDescription
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }

    private fun reserveProductSuccess() = AlertDialog.Builder(this)
        .setMessage(getString(R.string.reserved_product))
        .setPositiveButton(android.R.string.yes) { dialog, _ ->
            dialog.dismiss()
        }
        .show()

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
