package com.ame.lojinhatesteame.ui.category

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.annotation.VisibleForTesting
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.ame.lojinhatesteame.R
import com.ame.lojinhatesteame.data.model.Banner
import com.ame.lojinhatesteame.data.model.Category
import com.ame.lojinhatesteame.databinding.FragmentBannerBinding
import com.ame.lojinhatesteame.databinding.FragmentCategoryBinding
import com.ame.lojinhatesteame.injection.Injectable
import com.ame.lojinhatesteame.ui.banner.BannerAdapter
import com.ame.lojinhatesteame.ui.banner.BannerViewModel
import com.ame.lojinhatesteame.ui.product.ProductByCategoryActivity
import com.ame.lojinhatesteame.ui.product.ProductByCategoryActivity.Companion.CATEGORY
import com.ame.lojinhatesteame.util.BindingAdapters
import javax.inject.Inject

class CategoryFragment : Fragment() , Injectable {

    @Inject
    @VisibleForTesting
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var dataBinding: FragmentCategoryBinding

    private lateinit var viewModel: CategoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(CategoryViewModel::class.java)
    }

    override fun onStart() {
        super.onStart()
        viewModel.onLoadCategories()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_category, container, false)
        dataBinding = FragmentCategoryBinding.bind(rootView)
        dataBinding.recyclerViewCategory.adapter = CategoryAdapter(emptyList()) {category ->
            val intent = Intent(activity, ProductByCategoryActivity::class.java)
            intent.putExtra(CATEGORY, category)
            startActivity(intent)
        }
        dataBinding.recyclerViewCategory.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        dataBinding.viewModel = viewModel

        val observer = Observer<MutableList<Category>> { t ->
            t?.apply {
                BindingAdapters.setItems(dataBinding.recyclerViewCategory, toMutableList())
            }
        }
        viewModel.categoryLiveData.observe(this, observer)
        viewModel.showErrorToast.observe(this, Observer {
            Toast.makeText(activity, it, Toast.LENGTH_LONG).show()
        })

        return dataBinding.root
    }
}
