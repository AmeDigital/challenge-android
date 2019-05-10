package com.wagnermessias.olodjinha.feature.about

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wagnermessias.olodjinha.R
import com.wagnermessias.olodjinha.core.model.Product
import com.wagnermessias.olodjinha.feature.products.ProductDetailActivity
import kotlinx.android.synthetic.main.about_activity.*

class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.about_activity)
        initListeners()
    }

    private fun initListeners() {


        toolbar_about.setNavigationOnClickListener {
            finish()
        }
    }

    companion object {
        fun newInstance(
            context: Context
        ) = Intent(context, AboutActivity::class.java)
    }
}
