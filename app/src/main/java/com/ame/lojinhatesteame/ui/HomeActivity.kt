package com.ame.lojinhatesteame.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ame.lojinhatesteame.R
import com.ame.lojinhatesteame.ui.banner.BannerFragment
import com.ame.lojinhatesteame.ui.category.CategoryFragment
import com.ame.lojinhatesteame.ui.product.ProductBestSalesFragment
import com.ame.lojinhatesteame.util.NavigationViewController
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        navigationDrawer()

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.contentBannerSlider, BannerFragment())
                .commit()

            supportFragmentManager.beginTransaction()
                .add(R.id.contentCategory, CategoryFragment())
                .commit()

            supportFragmentManager.beginTransaction()
                .add(R.id.contentBestSales, ProductBestSalesFragment())
                .commit()
        }
    }

    private fun navigationDrawer() {
        val navigationViewController = NavigationViewController(this, toolbar)
        val menu = navigationView.menu.findItem(R.id.drawer_home)
        menu.isChecked = true
        navigationViewController.initNavigationDrawer(navigationView, drawer, this@HomeActivity)
        navigationView.inflateHeaderView(R.layout.drawer_header)
    }
}
