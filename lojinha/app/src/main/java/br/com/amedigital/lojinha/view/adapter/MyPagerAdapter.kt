package br.com.amedigital.lojinha.view.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import br.com.amedigital.lojinha.R
import br.com.amedigital.lojinha.model.Banner
import br.com.amedigital.lojinha.view.HomeFragment
import com.br.cinesky.model.BannerResponse
import com.br.cinesky.model.Datum
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso


class MyPagerAdapter(
    var bannerList: BannerResponse,
    homeFragment: HomeFragment
) : PagerAdapter() {

    override fun isViewFromObject(p0: View, p1: Any): Boolean {
        return p0 ==  p1
    }

    interface Callbacks {
        fun open(movie: Banner, position: Int)
    }

    override fun getCount(): Int {
        return bannerList.data!!.size
    }

    override fun instantiateItem(parent: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.page_layout, parent, false)
        parent.addView(view)
        binds(position, view)
        return view
    }

    private fun binds(position: Int, view: View) {
        val imaBanner: ImageView = view.findViewById(R.id.imgBanner)
        var context: Context = view.context
        var datum: Datum = bannerList.data!![position]

        Picasso.with(context)
            .load(datum.linkUrl)
            .error(R.drawable.blank)
            .into(imaBanner)

        view.setOnClickListener {
            val uris = Uri.parse(context.getString(R.string.url_submarino))
            val intents = Intent(Intent.ACTION_VIEW, uris)
            context.startActivity(intents)
        }
    }

    override fun destroyItem(parent: ViewGroup, position: Int, `object`: Any) {
        // Remove the view from view group specified position
        parent.removeView(`object` as View)
    }


}