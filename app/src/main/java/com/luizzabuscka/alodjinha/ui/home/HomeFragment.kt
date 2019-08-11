package com.luizzabuscka.alodjinha.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.luizzabuscka.alodjinha.R
import com.luizzabuscka.commons.models.Banner
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configBanners()
    }

    private fun configBanners() {
        vpBanners.adapter = BannersAdapter(
            listOf(
                Banner("https://images-submarino.b2w.io/spacey/2017/02/06/MainTop_GAMES_FEV17.png", "https://images-submarino.b2w.io/spacey/2017/02/06/MainTop_GAMES_FEV17.png"),
                Banner("https://images-submarino.b2w.io/spacey/2017/02/06/DESTAQUE_FULL_CARTAO_CASA_FEV.png", "https://images-submarino.b2w.io/spacey/2017/02/06/DESTAQUE_FULL_CARTAO_CASA_FEV.png"),
                Banner("https://images-submarino.b2w.io/spacey/2017/02/03/sub-home-dest-full-655x328-touch-play.png", "https://images-submarino.b2w.io/spacey/2017/02/03/sub-home-dest-full-655x328-touch-play.png")
            )
        )

        tabLayoutBanners.setupWithViewPager(vpBanners, true)
        tabLayoutBanners.bringToFront()
    }


}
