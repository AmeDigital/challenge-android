package com.leonardoalves.ametest.store.product

import android.os.Build
import android.os.Bundle
import android.text.Html
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.leonardoalves.ametest.R
import com.leonardoalves.ametest.store.viewmodel.StoreProductViewModel
import com.leonardoalves.ametest.utils.crossFade
import kotlinx.android.synthetic.main.activity_product.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

const val PRODUCT_ID_EXTRA = "product_id_extra"

class ProductActivity : AppCompatActivity(), ProductView {
    private val presenter : ProductPresenter by inject { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)
        intent.getIntExtra(PRODUCT_ID_EXTRA, -1).let { productId ->
            presenter.onCreate(productId)
        }
        fabReserve.setOnClickListener {
            presenter.reserveItem()
        }
    }

    override fun fillProductDetails(viewModel: StoreProductViewModel) {
        tvProductCategory.text = viewModel.categoryName
        tvProdcutName.text = viewModel.name
        tvProductPrice2.text = viewModel.price.toString()
        tvProductPriceFrom2.text = viewModel.originalPrice.toString()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            tvProductDescription.text = Html.fromHtml(viewModel.description, Html.FROM_HTML_MODE_COMPACT)
        } else {
            tvProductDescription.text = Html.fromHtml(viewModel.description)
        }
        Glide.with(this)
            .load(viewModel.picture)
            .crossFade()
            .into(toolbar_image)
    }
}