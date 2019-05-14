package com.br.cinesky.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getRootLayoutId())
        setupView(savedInstanceState)
    }

    abstract fun getRootLayoutId(): Int

    abstract fun setupView(savedInstanceState: Bundle?)
}