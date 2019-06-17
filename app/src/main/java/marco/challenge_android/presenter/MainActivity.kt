package marco.challenge_android.presenter

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import marco.challenge_android.R
import marco.challenge_android.presenter.home.AboutFragment
import marco.challenge_android.presenter.home.HomeFragment
import marco.challenge_android.presenter.home.MainContract
import marco.challenge_android.presenter.home.MainPresenter

class MainActivity : BaseActivity(), MainContract.View {

    override lateinit var presenter: MainContract.Presenter
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initToolbar(R.id.toolbar_main)
        with(supportActionBar) {
            this?.setDisplayHomeAsUpEnabled(true)
            this?.setDisplayShowTitleEnabled(false)
            this?.setHomeButtonEnabled(true)
        }
        initUI()
        addListeners()
    }

    override fun initUI() {
        presenter = MainPresenter(this)
        presenter.subscribe()
        toggle = ActionBarDrawerToggle(
            this,
            drawer_layout,
            toolbar,
            R.string.drawer_open,
            R.string.drawer_close
        )
    }

    override fun addListeners() {
        drawer_layout.addDrawerListener(toggle)
        with(nav_view) {
            itemIconTintList = null
            this.setNavigationItemSelectedListener {
                unCheckAllMenuItems(nav_view.menu)
                it.isChecked = true
                when (it.itemId) {
                    R.id.nav_home -> changeFragment(HomeFragment())
                    R.id.nav_about -> changeFragment(AboutFragment())
                }
                drawer_layout.closeDrawer(GravityCompat.START)
                return@setNavigationItemSelectedListener true
            }
        }
    }

    private fun unCheckAllMenuItems(menu: Menu) {
        val size = menu.size()
        for (i in 0 until size) {
            val item = menu.getItem(i)
            if (item.hasSubMenu()) {
                unCheckAllMenuItems(item.subMenu)
            } else {
                item.isChecked = false
            }
        }
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) drawer_layout.closeDrawer(GravityCompat.START)
        else super.onBackPressed()
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        toggle.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        toggle.onConfigurationChanged(newConfig)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (toggle.onOptionsItemSelected(item)) return true
        return super.onOptionsItemSelected(item)
    }

    override fun initHomeFragment() {
        changeFragment(HomeFragment())
    }

    override fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.container, fragment).commit()
    }

    companion object {

        fun newIntent(context: Context) = Intent(context, MainActivity::class.java)

    }

}
