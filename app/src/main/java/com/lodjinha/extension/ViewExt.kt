package com.lodjinha.extension

import android.app.Activity
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.lodjinha.utils.Event

/**
 * Transforms static java function SnackBar.make() to an extension function on View.
 */
fun Fragment.showSnackBar(snackBarText: String, timeLength: Int) =
    activity?.let {
        Snackbar.make(it.findViewById<View>(android.R.id.content), snackBarText, timeLength).show()
    }

fun Activity.showSnackBar(snackBarText: String, timeLength: Int) =
    Snackbar.make(this.findViewById<View>(android.R.id.content), snackBarText, timeLength).show()

/**
 * Triggers a snackBar message when the value contained by snackBarTaskMessageLiveEvent is modified.
 */
fun Fragment.setupSnackBar(lifecycleOwner: LifecycleOwner, snackBarEvent: LiveData<Event<Int>>, timeLength: Int) =
    snackBarEvent.observe(lifecycleOwner, Observer { event ->
        event.getContentIfNotHandled()?.let { res ->
            context?.let { showSnackBar(it.getString(res), timeLength) }
        }
    })

fun Activity.setupSnackBar(lifecycleOwner: LifecycleOwner, snackBarEvent: LiveData<Event<Int>>, timeLength: Int) =
    snackBarEvent.observe(lifecycleOwner, Observer { event ->
        event.getContentIfNotHandled()?.let { res ->
            showSnackBar(this.getString(res), timeLength)
        }
    })
