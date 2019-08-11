package com.luizzabuscka.alodjinha.ui.home

import android.os.Bundle
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import android.view.MenuItem
import android.view.View
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.luizzabuscka.alodjinha.R
import com.luizzabuscka.alodjinha.ui.about.AboutFragment
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_home.*


class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val homeFragment = HomeFragment()
    private val aboutFragment = AboutFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.itemIconTintList = null
        nav_view.setNavigationItemSelectedListener(this)
        configFragment(homeFragment)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> {
                llBar.visibility = View.VISIBLE
                supportActionBar?.title = ""
                configFragment(homeFragment)
            }
            R.id.nav_about -> {
                llBar.visibility = View.GONE
                supportActionBar?.title = getString(R.string.title_about)
                configFragment(aboutFragment)
            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun configFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.framelayout_home, fragment).commit()
    }


}
