package com.leonardoalves.ametest.store.category

import android.app.Activity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.leonardoalves.ametest.R
import com.leonardoalves.ametest.custom.*
import com.leonardoalves.ametest.store.viewholder.STORE_PRODUCT_VIEW_ID
import com.leonardoalves.ametest.store.viewholder.StoreProductViewHolder
import com.leonardoalves.ametest.store.viewmodel.StoreCategoryViewModel
import com.leonardoalves.ametest.store.viewmodel.StoreProductViewModel
import com.leonardoalves.ametest.utils.DialogUtils
import kotlinx.android.synthetic.main.activity_category.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf



const val CATEGORY_SERIALIZABLE_EXTRA = "category_serializable_extra"

class CategoryActivity : AppCompatActivity(), CategoryView {

    private lateinit var categoryAdapter: RecyclerViewAdapter
    private val presenter : CategoryPresenter by inject { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        setupList()
        (intent?.getSerializableExtra(CATEGORY_SERIALIZABLE_EXTRA) as? StoreCategoryViewModel)?.let {
            presenter.onCreate(it)
        }
    }

    private fun setupList() {
        categoryAdapter = RecyclerViewAdapter(object : ViewHolderFactory {
            override fun getType(viewModel: ViewModel): Int = when(viewModel){
                is StoreProductViewModel -> STORE_PRODUCT_VIEW_ID
                else -> throw IllegalArgumentException()
            }

            override fun getHolder(viewType: Int, view: View): ViewHolder<*> = when(viewType){
                STORE_PRODUCT_VIEW_ID -> StoreProductViewHolder(view, onProductClicked)
                else -> throw IllegalArgumentException()
            }
        })
        val linearLayoutManager = LinearLayoutManager(this)
        rvCategories.apply {
            adapter = categoryAdapter
            layoutManager = linearLayoutManager
            addItemDecoration(ListDividerItemDecoration(context))
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (linearLayoutManager.itemCount <= linearLayoutManager.findLastVisibleItemPosition() + 2){
                        presenter.onScrollBeyond()
                    }
                }
            })
        }
        srlCategories.setOnRefreshListener {
            presenter.refresh()
        }
    }
    private val onProductClicked: ViewHolder.Listener<StoreProductViewModel> = object : ViewHolder.Listener<StoreProductViewModel>{
        override fun onClick(viewModel: StoreProductViewModel) {
            val navController = Navigation.findNavController(this@CategoryActivity, R.id.fNavHost)
            navController.navigate(R.id.action_storeFragment_to_productActivity)
        }

    }

    override fun setupToolbar(title: String){
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = title
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {finish(); true}
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun addItems(items: List<ViewModel>, resetList : Boolean){
        if (resetList) categoryAdapter.setItems(items) else categoryAdapter.addItems(items)
    }

    override fun startLoading() {
        srlCategories.isRefreshing = true
    }

    override fun stopLoading() {
        srlCategories.isRefreshing = false
    }

    override fun showErrorCriticalMessage(){
        DialogUtils.showDialog(this, getString(R.string.product_not_found)) {finish()}
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }
}