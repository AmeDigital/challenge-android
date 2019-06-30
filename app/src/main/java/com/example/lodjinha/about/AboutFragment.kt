package com.example.task.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.lodjinha.R

class AboutFragment : Fragment() {
    companion object {
        fun newInstance(): AboutFragment {
            val fragment = AboutFragment()

            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_about, container, false)

        return rootView
    }
}
