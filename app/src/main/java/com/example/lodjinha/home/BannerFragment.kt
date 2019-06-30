package com.example.lodjinha.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.lodjinha.modelVO.BannerVO
import com.squareup.picasso.Picasso
import com.example.lodjinha.R
import kotlinx.android.synthetic.main.banner_fragment.*

class BannerFragment : Fragment() {
    companion object {
        private const val BANNER_OBJECT = "bannerObject"

        fun newInstance(bannerVO: BannerVO) = BannerFragment().apply {
            arguments = Bundle().apply {
                putParcelable(BANNER_OBJECT, bannerVO)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            Picasso.get().load((it[BANNER_OBJECT] as BannerVO).imageUrl).into(view.findViewById<ImageView>(
                R.id.img_pass
            ))
            val url = (it[BANNER_OBJECT] as BannerVO).destinationUrl
            img_pass?.setOnClickListener {
                val openURL = Intent(Intent.ACTION_VIEW)
                openURL.data = Uri.parse(url)
                startActivity(openURL)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.banner_fragment, container, false)
    }

}