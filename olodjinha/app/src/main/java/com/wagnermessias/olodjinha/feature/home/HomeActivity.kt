package com.wagnermessias.olodjinha.feature.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.google.android.material.navigation.NavigationView
import com.viewpagerindicator.CirclePageIndicator
import com.wagnermessias.olodjinha.R
import com.wagnermessias.olodjinha.core.extensions.OnItemClickListener
import com.wagnermessias.olodjinha.core.extensions.addOnItemClickListener
import com.wagnermessias.olodjinha.core.model.*
import com.wagnermessias.olodjinha.feature.about.AboutActivity
import com.wagnermessias.olodjinha.feature.products.ProductByCategoryActivity
import com.wagnermessias.olodjinha.feature.products.ProductDetailActivity
import com.wagnermessias.olodjinha.feature.products.ProductsAdapter
import kotlinx.android.synthetic.main.home_content.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var bannersPager: ViewPager
    private lateinit var rvCategories: RecyclerView
    private lateinit var rvProducts: RecyclerView

    private lateinit var context: Context

    private lateinit var categoriesList: List<Category>

    private val homeViewModel: HomeViewModel by viewModel()

    private var productsList: ArrayList<Product> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)
        context = this
        setupViews()
        showProgress(true)
        observeViewModel()
        loadData()
        initListeners()
    }

    private fun initListeners() {
        rvCategories.addOnItemClickListener(object : OnItemClickListener {
            override fun onItemClicked(position: Int, view: View) {
                startActivity(ProductByCategoryActivity.newInstance(context,
                                                                    categoriesList[position]))
            }
        })

        rvProducts.addOnItemClickListener(object : OnItemClickListener {
            override fun onItemClicked(position: Int, view: View) {
                startActivity(ProductDetailActivity.newInstance(this@HomeActivity,                            productsList[position]))
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
            }
        })
    }

    private fun setupViews() {

        bannersPager = findViewById(R.id.bannersPager)
        rvCategories = findViewById(R.id.categories_list)
        rvProducts = findViewById(R.id.products_list)
        rvCategories.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvProducts.layoutManager = LinearLayoutManager(this)

        val toolbar: Toolbar = findViewById(R.id.toolbar_home)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
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
            override fun onPageScrolled(position: Int,
                                        positionOffset: Float,
                                        positionOffsetPixels: Int) {}
            override fun onPageSelected(position: Int) {
                Toast.makeText(context,"Test",Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun setCategories(categories: Categories) {
        rvCategories.adapter = CategoriesAdapter(categories.list)
        categoriesList = categories.list
    }

    private fun setProducts(products: Products) {
        productsList = products.list
        rvProducts.adapter = ProductsAdapter(products.list, this)
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
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    private fun showProgress(show: Boolean) {
        progress_home.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.nav_home -> {
                finish()
                startActivity(Intent(context, HomeActivity::class.java))
            }
            R.id.nav_about -> {
                startActivity(Intent(context, AboutActivity::class.java))
            }
        }

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}