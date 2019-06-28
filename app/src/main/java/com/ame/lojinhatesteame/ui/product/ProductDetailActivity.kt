package com.ame.lojinhatesteame.ui.product

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.ame.lojinhatesteame.R
import com.ame.lojinhatesteame.data.model.Product

class ProductDetailActivity : AppCompatActivity() {

    companion object {
        const val PRODUCT = "product"
    }

    var product: Product? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_product_detail)

        getDataExtra(intent.extras)

    }

    private fun getDataExtra(extras: Bundle?) {
        product = extras?.getSerializable(PRODUCT) as Product
        product?.apply {

            val bundle = Bundle()
            bundle.putSerializable(PRODUCT ,this)

            val productDetailFragment = ProductDetailFragment()
            productDetailFragment.arguments = bundle
            supportFragmentManager.beginTransaction()
                .add(R.id.contentProductDetail, productDetailFragment)
                .commit()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
