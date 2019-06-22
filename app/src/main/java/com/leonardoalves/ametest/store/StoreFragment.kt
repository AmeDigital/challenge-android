package com.leonardoalves.ametest.store

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.leonardoalves.ametest.R
import com.leonardoalves.ametest.custom.RecyclerViewAdapter
import com.leonardoalves.ametest.custom.ViewHolder
import com.leonardoalves.ametest.custom.ViewHolderFactory
import com.leonardoalves.ametest.custom.ViewModel
import com.leonardoalves.ametest.store.viewholder.STORE_BANNER_LIST_VIEW_ID
import com.leonardoalves.ametest.store.viewholder.StoreBannerViewHolder
import com.leonardoalves.ametest.store.viewmodel.BannerItemViewModel
import com.leonardoalves.ametest.store.viewmodel.StoreBannerViewModel
import kotlinx.android.synthetic.main.fragment_store.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class StoreFragment : Fragment(), StoreView {

    private val presenter : StorePresenter by inject { parametersOf(this) }
    private var storeAdapter : RecyclerViewAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_store, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupList()
        presenter.onCreate()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    private fun setupList() {
        storeAdapter = RecyclerViewAdapter(object : ViewHolderFactory{
            override fun getType(viewModel: ViewModel): Int = when(viewModel){
                is StoreBannerViewModel -> STORE_BANNER_LIST_VIEW_ID
                else -> throw IllegalArgumentException()
            }

            override fun getHolder(viewType: Int, view: View): ViewHolder<*> = when(viewType){
                STORE_BANNER_LIST_VIEW_ID -> StoreBannerViewHolder(view, onBannerClicked)
                else -> throw IllegalArgumentException()
            }
        })
        rvStoreList.apply {
            adapter = storeAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private val onBannerClicked = object : ViewHolder.Listener<BannerItemViewModel>{
        override fun onClick(viewModel: BannerItemViewModel) {
            val openURL = Intent(Intent.ACTION_VIEW)
            openURL.data = Uri.parse(viewModel.link)
            startActivity(openURL)
        }
    }

    override fun setItems(viewModel: List<ViewModel>) {
        storeAdapter?.setItems(viewModel)
    }
}