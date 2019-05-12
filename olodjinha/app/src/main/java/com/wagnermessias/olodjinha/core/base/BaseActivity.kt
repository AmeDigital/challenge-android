package com.wagnermessias.olodjinha.core.base

import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.wagnermessias.olodjinha.R

open class BaseActivity: AppCompatActivity() {

    protected fun showAlertDialog(stringId: Int) {
        AlertDialog.Builder(this).apply {
            setCancelable(false)
            setMessage(getString(stringId))
            setPositiveButton(getString(R.string.reservation_alert_button_ok)) { dialog, _ ->
                dialog.dismiss()
            }
            create().show()
        }
    }

    protected fun showAlertDialog(message: String) {
        AlertDialog.Builder(this).apply {
            setCancelable(false)
            setMessage(message)
            setPositiveButton(getString(R.string.reservation_alert_button_ok)) { dialog, _ ->
                dialog.dismiss()
            }
            create().show()
        }
    }

}