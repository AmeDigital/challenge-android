package com.ame.lojinhatesteame.ui.product

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.ame.lojinhatesteame.R
import com.ame.lojinhatesteame.data.model.Category

class ProductByCategoryActivity : AppCompatActivity() {

    companion object {
        const val CATEGORY = "category"
    }

    var category : Category? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_by_category)

        getDataExtra(intent.extras)
    }

    private fun getDataExtra(extras: Bundle?) {
        category = extras?.getSerializable(CATEGORY) as Category
        category?.apply {

            val bundle = Bundle()
            bundle.putSerializable(CATEGORY ,category)

            val productByCategoryFragment = ProductByCategoryFragment()
            productByCategoryFragment.arguments = bundle
                supportFragmentManager.beginTransaction()
                    .add(R.id.contentProductByCategory, productByCategoryFragment)
                    .commit()
            setToolbar(description.toLowerCase())
        }
    }

    private fun setToolbar(title : String) {
        supportActionBar?.apply {
            this.title = title
            setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
