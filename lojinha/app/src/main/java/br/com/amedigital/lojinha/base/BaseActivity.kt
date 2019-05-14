package com.br.cinesky.base

import android.app.Dialog
import android.arch.lifecycle.Observer
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Gravity
import android.view.Window
import android.view.WindowManager
import br.com.amedigital.lojinha.R

abstract class BaseActivity : AppCompatActivity() {
    private val TAG = BaseActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getRootLayoutId())
        setupView(savedInstanceState)
    }

    abstract fun getRootLayoutId(): Int

    abstract fun setupView(savedInstanceState: Bundle?)

    private fun showQuestFailure(throwable: Throwable) {
        Log.i(TAG, "showFailure: " + throwable.message)
        if (throwable.message != null) {

        }
    }

    private lateinit var mProgressDialog: Dialog

    private fun showLoadingDialog() {
        if (!this::mProgressDialog.isInitialized) {
            mProgressDialog = Dialog(this)
            mProgressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            mProgressDialog.setCancelable(false)
            mProgressDialog.setContentView(R.layout.custom_progress_loading)
            if (mProgressDialog.window != null && isFinishing) {
                mProgressDialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    val lp = WindowManager.LayoutParams()
                    lp.width = WindowManager.LayoutParams.WRAP_CONTENT
                    lp.height = WindowManager.LayoutParams.WRAP_CONTENT
                    lp.gravity = Gravity.CENTER
                    mProgressDialog.window!!.attributes = lp
                } else {
                    mProgressDialog.window!!.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
                }
                if (!mProgressDialog.isShowing) {
                    mProgressDialog.show()
                    Log.i(TAG, "setProgress: success")
                }
            }
        }
        if (isFinishing && this::mProgressDialog.isInitialized && !mProgressDialog.isShowing) {
            try {
                mProgressDialog.show()
                Log.i(TAG, "setProgress: success")
            } catch (ex: Exception) {
                ex.printStackTrace()
            }

        }
    }

    private fun hideLoadingDialog() {
        if (this::mProgressDialog.isInitialized && mProgressDialog.isShowing) {
            mProgressDialog.dismiss()
        }
    }
    fun setObserveLive(viewModel: BaseViewModel) {
        viewModel.eventLoading.observe(this, Observer {
            if (it != null) {
                if (it.getContentIfNotHandled() != null) {
                    if (it.peekContent()) {
                        showLoadingDialog()
                    } else {
                        hideLoadingDialog()
                    }
                }
            }
        })
        viewModel.eventFailure.observe(this, Observer {
            if (it != null) {
                if (it.getContentIfNotHandled() != null) {
                    showQuestFailure(it.peekContent())
                }
            }
        })
    }
}