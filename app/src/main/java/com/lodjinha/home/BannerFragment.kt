package com.lodjinha.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.lodjinha.databinding.FragmentBannerBinding
import com.lodjinha.model.Banner

class BannerFragment : Fragment() {

    private lateinit var binding: FragmentBannerBinding

    companion object {
        const val BANNERS_KEY = "BANNERS_KEY"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentBannerBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val banner = this.arguments?.getString(BANNERS_KEY, "")
        if (banner != "") {
            binding.banner = Gson().fromJson<Banner>(banner, Banner::class.java)
        }
    }
}
