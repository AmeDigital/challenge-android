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
import com.leonardoalves.ametest.custom.*
import com.leonardoalves.ametest.store.viewholder.*
import com.leonardoalves.ametest.store.viewmodel.*
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
                is StoreHeaderViewModel -> STORE_HEADER_VIEW_ID
                is StoreCategoriesListViewModel -> STORE_CATEGORIES_LIST_VIEW_ID
                is StoreProductViewModel -> STORE_PRODUCT_VIEW_ID
                else -> throw IllegalArgumentException()
            }

            override fun getHolder(viewType: Int, view: View): ViewHolder<*> = when(viewType){
                STORE_BANNER_LIST_VIEW_ID -> StoreBannerViewHolder(view, onBannerClicked)
                STORE_HEADER_VIEW_ID -> StoreHeaderViewHolder(view)
                STORE_CATEGORIES_LIST_VIEW_ID -> StoreCategoriesListViewHolder(view)
                STORE_PRODUCT_VIEW_ID -> StoreProductViewHolder(view)
                else -> throw IllegalArgumentException()
            }
        })
        rvStoreList.apply {
            adapter = storeAdapter
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(ListDividerItemDecoration(context))
        }
        srlStoreList.setOnRefreshListener {
            presenter.refresh()
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

    override fun startLoading() {
        srlStoreList.isRefreshing = true
    }

    override fun stopLoading() {
        srlStoreList.isRefreshing = false
    }
}