package com.luizzabuscka.alodjinha.ui.product

import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.core.text.HtmlCompat
import androidx.lifecycle.*
import com.bumptech.glide.Glide
import com.luizzabuscka.alodjinha.R
import com.luizzabuscka.commons.model.Product
import com.luizzabuscka.viewmodel.ProductViewModel
import kotlinx.android.synthetic.main.activity_product.*
import kotlinx.android.synthetic.main.activity_product.ivProduct
import org.jetbrains.anko.alert
import org.jetbrains.anko.indeterminateProgressDialog
import org.jetbrains.anko.okButton
import org.koin.android.viewmodel.ext.android.viewModel

class ProductActivity : AppCompatActivity(), LifecycleOwner {

    private val product by lazy {
        intent?.extras?.get("product") as Product
    }

    private val progressDialog by lazy {
        indeterminateProgressDialog(
            getString(R.string.dialog_please_wait),
            getString(R.string.dialog_loading)
        )
    }

    private val productViewModel: ProductViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = product.category.description

        configFab()

        configProduct(product)
    }

    private fun configFab() {
        fabReserve.setOnClickListener {
            reserveProduct()
        }
    }

    private fun reserveProduct() {
        progressDialog.show()
        productViewModel.reserveProduct(product.id).observe(this, Observer { success ->
            progressDialog.hide()
            if (success) {
                alert(getString(R.string.reserve_success)) {
                    this.isCancelable = false
                    okButton {
                        // do nothing
                    }
                }.show()
            } else {
                alert(getString(R.string.failed_to_reserve)) {
                    this.isCancelable = false
                    positiveButton(getString(R.string.dialog_button_try_again)) {
                        reserveProduct()
                    }
                }.show()
            }
        })
    }

    private fun configProduct(product: Product) {
        Glide
            .with(ivProduct.context)
            .load(product.urlImage)
            .placeholder(R.drawable.placeholder)
            .centerInside()
            .into(ivProduct)

        tvName.text = product.name
        tvDescription.text = HtmlCompat.fromHtml(product.description, HtmlCompat.FROM_HTML_MODE_LEGACY)
        tvPrice.text = getString(R.string.price_to, product.price)
        tvPriceFrom.text = getString(R.string.price_from, product.priceFrom)
        tvPriceFrom.paintFlags = tvPriceFrom.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    private val lifecycle = LifecycleRegistry(this)
    override fun getLifecycle(): Lifecycle {
        return lifecycle
    }
}
