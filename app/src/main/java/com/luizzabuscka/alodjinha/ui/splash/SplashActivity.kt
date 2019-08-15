package com.luizzabuscka.alodjinha.ui.splash

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import com.luizzabuscka.alodjinha.R
import com.luizzabuscka.alodjinha.ui.home.HomeActivity
import kotlinx.android.synthetic.main.activity_splash.*
import org.jetbrains.anko.startActivity

class SplashActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        ivLogo.startAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_in))
        callNextActivity()
    }

    private fun callNextActivity() {
        Handler().postDelayed({
            startActivity<HomeActivity>()
            onBackPressed()
        }, 3000)
    }
}
