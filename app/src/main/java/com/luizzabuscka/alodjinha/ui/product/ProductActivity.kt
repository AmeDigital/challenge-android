package com.luizzabuscka.alodjinha.ui.product

import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.core.text.HtmlCompat
import com.bumptech.glide.Glide
import com.luizzabuscka.alodjinha.R
import com.luizzabuscka.commons.model.Product
import kotlinx.android.synthetic.main.activity_product.*
import kotlinx.android.synthetic.main.activity_product.ivProduct

class ProductActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        val product = intent?.extras?.get("product") as Product

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
        //RESERVE PRODUCT
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
}
