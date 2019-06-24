package com.leonardoalves.ametest

import android.os.Bundle
import android.util.Log
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import android.view.MenuItem
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        val toggle = ActionBarDrawerToggle(
            this, dlMainMenu, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        dlMainMenu.addDrawerListener(toggle)
        toggle.syncState()

        nvMainMenu.setNavigationItemSelectedListener(this)
        nvMainMenu.itemIconTintList = null
        navController = Navigation.findNavController(this, R.id.fNavHost)
    }

    override fun onBackPressed() {
        if (dlMainMenu.isDrawerOpen(GravityCompat.START)) {
            dlMainMenu.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.iHome -> {
                navController.navigate(R.id.nav_action_to_store)
            }
            R.id.iAbout -> {
                navController.navigate(R.id.nav_action_to_about)
            }
        }
        dlMainMenu.closeDrawer(GravityCompat.START)
        return true
    }
}
