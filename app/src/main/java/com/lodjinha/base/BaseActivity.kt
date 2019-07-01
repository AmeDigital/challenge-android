package com.lodjinha.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.lodjinha.extension.setupSnackBar

abstract class BaseActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupSnackBar(this, getViewModel().snackBarError, Snackbar.LENGTH_LONG)
    }

    abstract fun getViewModel(): BaseViewModel
}
