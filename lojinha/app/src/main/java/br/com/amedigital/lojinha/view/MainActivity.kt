package br.com.amedigital.lojinha.view

import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBar
import android.view.MenuItem
import android.widget.Toast
import br.com.amedigital.lojinha.R
import com.br.cinesky.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar.*

class MainActivity : BaseActivity() {

    override fun getRootLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun setupView(savedInstanceState: Bundle?) {
        toolbar.title = "a Lojinha"
        toolbar.logo = resources.getDrawable(R.drawable.logo_menu)
        setSupportActionBar(toolbar)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_fragment, HomeFragment.newInstance())
                .commitAllowingStateLoss()
        }

        val actionbar: ActionBar? = supportActionBar
        actionbar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.mipmap.baseline_menu_white_18dp)
        }



        nav_view.setNavigationItemSelectedListener { menuItem ->
            menuItem.isChecked = true
            drawer_layout.closeDrawers()

            when (menuItem.itemId) {
                R.id.nav_profile -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_fragment, HomeFragment.newInstance())
                        .commitAllowingStateLoss()
                }
                R.id.nav_wallet -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_fragment, AboutFragment.newInstance())
                        .commitAllowingStateLoss()
                }
            }

            true
        }
    }

}
