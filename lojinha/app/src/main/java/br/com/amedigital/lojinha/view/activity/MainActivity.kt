package br.com.amedigital.lojinha.view.activity

import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBar
import android.view.MenuItem
import br.com.amedigital.lojinha.R
import br.com.amedigital.lojinha.view.fragment.AboutFragment
import br.com.amedigital.lojinha.view.fragment.HomeFragment
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                drawer_layout.openDrawer(GravityCompat.START)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}
