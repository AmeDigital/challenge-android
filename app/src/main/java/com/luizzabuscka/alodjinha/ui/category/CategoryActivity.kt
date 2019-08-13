package com.luizzabuscka.alodjinha.ui.category

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.luizzabuscka.alodjinha.R
import com.luizzabuscka.alodjinha.components.adapters.ProductsAdapter
import com.luizzabuscka.commons.mock.mockProductsBestSellers
import com.luizzabuscka.commons.model.Category
import com.luizzabuscka.commons.model.Product
import kotlinx.android.synthetic.main.activity_category.*

class CategoryActivity : AppCompatActivity() {

    private val items = arrayListOf<Product>()
    private lateinit var adapter: ProductsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        val category = intent?.extras?.get("category") as Category

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = category.description

        items.addAll(mockProductsBestSellers())
        configProducts()
    }

    private fun configProducts() {
        adapter = ProductsAdapter(items)
        rvProducts.adapter = adapter
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }


}
