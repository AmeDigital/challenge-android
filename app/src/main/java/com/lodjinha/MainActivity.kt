package com.lodjinha

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import com.google.android.material.navigation.NavigationView
import com.lodjinha.about.AboutFragment
import com.lodjinha.databinding.ActivityMainBinding
import com.lodjinha.home.HomeFragment

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMainBinding
    private val fragments = listOf(HomeFragment(), AboutFragment())
    private var positionActiveFragment: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initViews()
        configFragments()
        changeFragment(0)
    }

    private fun initViews() {
        setSupportActionBar(binding.toolbar)
        binding.toolbar.setTitleTextAppearance(this, R.style.ToolbarStyle)

        val toggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            binding.toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        binding.navView.setNavigationItemSelectedListener(this)
    }

    private fun configFragments() {
        for (position in fragments.size - 1 downTo 0) {
            supportFragmentManager
                .beginTransaction()
                .add(binding.fragmentContainer.id, fragments[position], fragments[position].tag).apply {
                if (position == 0) commit() else hide(fragments[position]).commit()
            }
        }
    }

    override fun onBackPressed() {
        val drawerLayout = binding.drawerLayout
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        changeFragment(
            selectedPosition = when (item.itemId) {
                R.id.nav_home -> 0
                R.id.nav_about -> 1
                else -> 1
            }
        )
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun changeFragment(selectedPosition: Int) = with(supportFragmentManager.beginTransaction()) {
        hide(fragments[positionActiveFragment]).show(fragments[selectedPosition]).commit()
        positionActiveFragment = selectedPosition
        supportActionBar?.title = getString(if (selectedPosition == 1) R.string.about else R.string.app_name)
    }
}
