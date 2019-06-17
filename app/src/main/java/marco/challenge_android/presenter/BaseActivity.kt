package marco.challenge_android.presenter

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View

abstract class BaseActivity : AppCompatActivity() {

    abstract fun initUI()
    abstract fun addListeners()

    protected lateinit var toolbar: Toolbar

    protected fun initToolbar(id: Int) {
        toolbar = findViewById<View>(id) as Toolbar
        setSupportActionBar(toolbar)
    }

}