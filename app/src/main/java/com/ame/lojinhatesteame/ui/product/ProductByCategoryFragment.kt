package com.ame.lojinhatesteame.ui.product


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.annotation.VisibleForTesting
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.ame.lojinhatesteame.R
import com.ame.lojinhatesteame.data.model.Category
import com.ame.lojinhatesteame.data.model.Product
import com.ame.lojinhatesteame.databinding.FragmentProductByCategoryBinding
import com.ame.lojinhatesteame.injection.Injectable
import com.ame.lojinhatesteame.ui.product.ProductByCategoryActivity.Companion.CATEGORY
import com.ame.lojinhatesteame.util.BindingAdapters
import javax.inject.Inject

class ProductByCategoryFragment  : Fragment() , Injectable {

    @Inject
    @VisibleForTesting
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var dataBinding:  FragmentProductByCategoryBinding

    private lateinit var viewModel: ProductViewModel

    private var category: Category? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.apply {
            category = getSerializable(CATEGORY) as Category
        }

        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(ProductViewModel::class.java)
    }

    override fun onStart() {
        super.onStart()
        category?.apply {
            viewModel.onLoadProductByCategory(id)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_product_by_category, container, false)
        dataBinding = FragmentProductByCategoryBinding.bind(rootView)
        dataBinding.recyclerViewProductCategory.adapter = ProductAdapter(emptyList()) {product ->

        }
        dataBinding.recyclerViewProductCategory.layoutManager = LinearLayoutManager(activity)
        dataBinding.viewModel = viewModel

        val observer = Observer<MutableList<Product>> { t ->
            t?.apply {
                BindingAdapters.setItems(dataBinding.recyclerViewProductCategory, toMutableList())
            }
        }
        viewModel.productLiveData.observe(this, observer)
        viewModel.showToast.observe(this, Observer {
            Toast.makeText(activity, it, Toast.LENGTH_LONG).show()
        })

        return dataBinding.root
    }

}
