package com.ame.lojinhatesteame.ui.product

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
import com.ame.lojinhatesteame.data.model.Product
import com.ame.lojinhatesteame.databinding.FragmentProductBestSalesBinding
import com.ame.lojinhatesteame.injection.Injectable
import com.ame.lojinhatesteame.ui.product.ProductDetailActivity.Companion.PRODUCT
import com.ame.lojinhatesteame.util.BindingAdapters
import javax.inject.Inject


class ProductBestSalesFragment :  Fragment() , Injectable {
    @Inject
    @VisibleForTesting
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var dataBinding: FragmentProductBestSalesBinding

    private lateinit var viewModel: ProductViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(ProductViewModel::class.java)
    }

    override fun onStart() {
        super.onStart()
        viewModel.onLoadBestSales()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_product_best_sales, container, false)
        dataBinding = FragmentProductBestSalesBinding.bind(rootView)
        dataBinding.recyclerViewBestSales.adapter = ProductAdapter(emptyList()) {product ->
            val intent = Intent(activity, ProductDetailActivity::class.java)
            intent.putExtra(PRODUCT, product)
            startActivity(intent)
        }
        dataBinding.recyclerViewBestSales.layoutManager = LinearLayoutManager(activity)
        dataBinding.viewModel = viewModel

        val observer = Observer<MutableList<Product>> { t ->
            t?.apply {
                BindingAdapters.setItems(dataBinding.recyclerViewBestSales, toMutableList())
            }
        }
        viewModel.productLiveData.observe(this, observer)
        viewModel.showToast.observe(this, Observer {
            Toast.makeText(activity, it, Toast.LENGTH_LONG).show()
        })

        return dataBinding.root
    }
}
