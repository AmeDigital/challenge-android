package com.ame.lojinhatesteame.ui.banner


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.annotation.VisibleForTesting
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.ame.lojinhatesteame.R
import com.ame.lojinhatesteame.data.model.Banner
import com.ame.lojinhatesteame.databinding.FragmentBannerBinding
import com.ame.lojinhatesteame.injection.Injectable
import javax.inject.Inject


class BannerFragment : Fragment(), Injectable {

    @Inject
    @VisibleForTesting
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var dataBinding: FragmentBannerBinding

    private lateinit var viewModel: BannerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(BannerViewModel::class.java)
    }

    override fun onStart() {
        super.onStart()
        viewModel.onLoadBanners()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_banner, container, false)
        dataBinding = FragmentBannerBinding.bind(rootView)
        dataBinding.bannerSlider.setAdapter(BannerAdapter(emptyList()))
        dataBinding.bannerSlider.setInterval(5000)
        dataBinding.viewModel = viewModel

        val observer = Observer<MutableList<Banner>> { t ->
            t?.apply {
                dataBinding.bannerSlider.setAdapter(BannerAdapter(toMutableList()))
            }
        }
        viewModel.bannerLiveData.observe(this, observer)

        viewModel.showErrorToast.observe(this, Observer {
            Toast.makeText(activity, it, Toast.LENGTH_LONG).show()
        })

        return dataBinding.root
    }

}
