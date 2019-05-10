package com.wagnermessias.olodjinha.feature.products

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.snackbar.Snackbar
import com.wagnermessias.olodjinha.R
import com.wagnermessias.olodjinha.core.extensions.toCurrency
import com.wagnermessias.olodjinha.core.model.Product
import kotlinx.android.synthetic.main.content_product_detail.*
import kotlinx.android.synthetic.main.product_detail_activity.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class ProductDetailActivity : AppCompatActivity() {

    private lateinit var product: Product
    private val productsViewModel: ProductsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.product_detail_activity)
        showProgressAndDisableFab(true)
        initProductsData(intent.extras)
        setupViews()
        initListeners()
        observeViewModel()
    }

    private fun observeViewModel() {
        productsViewModel.viewState.observe(this, Observer {
            when (it) {
                is ProductsViewState.ReservationProduct -> showSuccessReservation()
            }
        })
    }

    private fun showSuccessReservation() {
        showProgressAndDisableFab(false)
        AlertDialog.Builder(this).apply {
            setCancelable(false)
            setMessage(getString(R.string.reservation_alert_msg_success))
            setPositiveButton(getString(R.string.reservation_alert_button_ok)) { dialog, which ->
                dialog.dismiss()
                finish()
            }
            create().show()
        }
    }

    private fun setupViews() {
        setSupportActionBar(toolbar_produc_detail)
        toolbar_layout.title = product.category.description
        toolbar_layout.setCollapsedTitleTextColor(
            ContextCompat.getColor(this@ProductDetailActivity,R.color.default_white)
        )
    }

    private fun initProductsData(bundle: Bundle) {
        val productNew = bundle.getSerializable(ProductDetailActivity.PRODUCT_INFO) as Product
        productNew.apply {
            product = this
            setProductDetail(this)
        }
    }

    private fun setProductDetail(product: Product) {
        toolbar_layout.apply {

            Glide.with(context)
                .load(product.urlImage)
                .placeholder(R.drawable.ic_placeholder)
                .into(image_product_detail)
            setExpandedTitleColor(
                ContextCompat.getColor(context,R.color.default_black)
            )
        }

        product_detail_name.text = product.name
        val priceformated = String.format(
            this@ProductDetailActivity.getResources().getString(R.string.price_from),
            product.priceFrom.toCurrency()
        )
        product_detail_price_from.text = HtmlCompat.fromHtml(priceformated, HtmlCompat.FROM_HTML_MODE_LEGACY)
        product_detail_price_per.text = product.pricePer.toCurrency()
        product_detail_desription.text = HtmlCompat.fromHtml(product.description, HtmlCompat.FROM_HTML_MODE_LEGACY)
        showProgressAndDisableFab(false)
    }

    private fun initListeners() {
        app_bar.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener {
            override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
                if (Math.abs(verticalOffset) - appBarLayout!!.getTotalScrollRange() == 0) {
                    toolbar_layout.title = product.category.description
                    toolbar_layout.setCollapsedTitleTextColor(ContextCompat.getColor(this@ProductDetailActivity,R.color.default_white_two))
                } else {
                    toolbar_layout.title = ""
                }
            }
        })

        fab.setOnClickListener { view ->
            productsViewModel.reservationProducts(product.id)
            showProgressAndDisableFab(true)
        }

        toolbar_produc_detail.setNavigationOnClickListener {
            finish()
        }
    }

    private fun showProgressAndDisableFab(show: Boolean) {
        fab.isEnabled = !show
        progress_detail.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun showError(){
       Toast.makeText(this@ProductDetailActivity,"erro",Toast.LENGTH_LONG).show()
    }

    companion object {
        private const val PRODUCT_INFO = "product_param"

        fun newInstance(
            context: Context,
            product: Product
        ) = Intent(context, ProductDetailActivity::class.java).apply {
            Bundle().apply {
                putSerializable(PRODUCT_INFO, product)
                putExtras(this)
            }
        }
    }
}
