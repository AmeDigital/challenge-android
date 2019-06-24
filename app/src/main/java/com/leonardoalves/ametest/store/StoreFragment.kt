package com.leonardoalves.ametest.store

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.leonardoalves.ametest.R
import com.leonardoalves.ametest.custom.*
import com.leonardoalves.ametest.store.category.CATEGORY_SERIALIZABLE_EXTRA
import com.leonardoalves.ametest.store.product.PRODUCT_ID_EXTRA
import com.leonardoalves.ametest.store.viewholder.*
import com.leonardoalves.ametest.store.viewmodel.*
import com.leonardoalves.ametest.utils.DialogUtils
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
                STORE_CATEGORIES_LIST_VIEW_ID -> StoreCategoriesListViewHolder(view, onCategoryClicked)
                STORE_PRODUCT_VIEW_ID -> StoreProductViewHolder(view, onProductClicked)
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

    private val onCategoryClicked: ViewHolder.Listener<StoreCategoryViewModel> = object : ViewHolder.Listener<StoreCategoryViewModel>{
        override fun onClick(viewModel: StoreCategoryViewModel) {
            val navController = Navigation.findNavController(activity as Activity, R.id.fNavHost)
            navController.navigate(R.id.action_storeFragment_to_categoryActivity,
                Bundle().apply { this.putSerializable(CATEGORY_SERIALIZABLE_EXTRA, viewModel) })
        }

    }

    private val onProductClicked: ViewHolder.Listener<StoreProductViewModel> = object : ViewHolder.Listener<StoreProductViewModel>{
        override fun onClick(viewModel: StoreProductViewModel) {
            val navController = Navigation.findNavController(activity as Activity, R.id.fNavHost)
            navController.navigate(R.id.action_storeFragment_to_productActivity,
                Bundle().apply { this.putInt(PRODUCT_ID_EXTRA, viewModel.id) })
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

    override fun showErrorMessage(){
        DialogUtils.showDialog(context, getString(R.string.store_loading_error)) {}
    }
}