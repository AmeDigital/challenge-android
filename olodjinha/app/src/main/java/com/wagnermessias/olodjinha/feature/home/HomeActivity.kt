package com.wagnermessias.olodjinha.feature.home

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.google.android.material.navigation.NavigationView
import com.viewpagerindicator.CirclePageIndicator
import com.wagnermessias.olodjinha.R
import com.wagnermessias.olodjinha.core.base.BaseActivity
import com.wagnermessias.olodjinha.core.extensions.OnItemClickListener
import com.wagnermessias.olodjinha.core.extensions.addOnItemClickListener
import com.wagnermessias.olodjinha.core.model.Category
import com.wagnermessias.olodjinha.core.model.Product
import com.wagnermessias.olodjinha.core.model.Banners
import com.wagnermessias.olodjinha.core.model.Products
import com.wagnermessias.olodjinha.core.model.Categories
import com.wagnermessias.olodjinha.feature.about.AboutActivity
import com.wagnermessias.olodjinha.feature.products.ProductsAdapter
import com.wagnermessias.olodjinha.feature.products.bycategory.ProductByCategoryActivity
import com.wagnermessias.olodjinha.feature.products.detail.ProductDetailActivity
import kotlinx.android.synthetic.main.home_app_bar.toolbar_home
import kotlinx.android.synthetic.main.home_content.categories_list
import kotlinx.android.synthetic.main.home_content.products_list
import kotlinx.android.synthetic.main.home_content.bannersPager
import kotlinx.android.synthetic.main.home_content.progress_home
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {


    private lateinit var categoriesList: List<Category>
    private var productsList: ArrayList<Product> = ArrayList()

    private val homeViewModel: HomeViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)
        setupViews()
        showProgress(true)
        observeViewModel()
        loadData()
        initListeners()
    }

    private fun initListeners() {
        categories_list.addOnItemClickListener(object : OnItemClickListener {
            override fun onItemClicked(position: Int, view: View) {
                startActivity(
                    ProductByCategoryActivity.newInstance(
                        this@HomeActivity,
                        categoriesList[position]
                    )
                )
            }
        })

        products_list.addOnItemClickListener(object : OnItemClickListener {
            override fun onItemClicked(position: Int, view: View) {
                startActivity(ProductDetailActivity.newInstance(this@HomeActivity, productsList[position]))
            }
        })
    }

    private fun loadData() {
        homeViewModel.loadCategories()
        homeViewModel.loadBanners()
        homeViewModel.loadBestSellers()
    }

    private fun observeViewModel() {
        homeViewModel.viewState.observe(this, Observer {
            when (it) {
                is HomeViewState.BannersList -> setBanners(it.banners)
                is HomeViewState.CategoriesList -> setCategories(it.categories)
                is HomeViewState.ProductsList -> setProducts(it.products)
                is HomeViewState.ServerErrorBanners -> showDialogErros(R.string.alert_error_server)
                is HomeViewState.NetworkError -> showDialogErros(R.string.alert_error_network)
            }
        })
    }

    private fun setupViews() {
        categories_list.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        products_list.layoutManager = LinearLayoutManager(this)


        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar_home,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )

        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener(this)
    }

    private fun setBanners(banners: Banners) {
        bannersPager.adapter = BannerAdapter(banners.list)
        val pageIndicatorView: CirclePageIndicator = findViewById(R.id.indicator)
        pageIndicatorView.setViewPager(bannersPager)
        bannersPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                //Toast.makeText(context,"Test",Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun setCategories(categories: Categories) {
        categories_list.adapter = CategoriesAdapter(categories.list)
        categoriesList = categories.list
    }

    private fun setProducts(products: Products) {
        productsList = products.list
        products_list.adapter = ProductsAdapter(products.list, this)
        showProgress(false)
    }

    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    fun showDialogErros(stringId: Int) {
        AlertDialog.Builder(this).apply {
            setCancelable(false)
            setMessage(getString(stringId))
            setPositiveButton(getString(R.string.alert_try_again)) { dialog, _ ->
                dialog.dismiss()
                loadData()
            }
            setNegativeButton(getString(R.string.alert_cancel)) { dialog, _ ->
                finish()
            }
            create().show()
        }
    }

    private fun showProgress(show: Boolean) {
        progress_home.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.nav_home -> {
                finish()
                startActivity(Intent(this@HomeActivity, HomeActivity::class.java))
            }
            R.id.nav_about -> {
                startActivity(AboutActivity.newInstance(this@HomeActivity))
            }
        }

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}