package com.ame.lojinhatesteame.ui.product


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.annotation.VisibleForTesting
import android.support.design.widget.AppBarLayout
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.ame.lojinhatesteame.R
import com.ame.lojinhatesteame.data.model.Product
import com.ame.lojinhatesteame.databinding.FragmentProductDetailBinding
import com.ame.lojinhatesteame.injection.Injectable
import com.ame.lojinhatesteame.ui.product.ProductDetailActivity.Companion.PRODUCT
import kotlinx.android.synthetic.main.fragment_product_detail.*
import javax.inject.Inject


class ProductDetailFragment :  Fragment() , Injectable {

    @Inject
    @VisibleForTesting
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var dataBinding: FragmentProductDetailBinding

    private lateinit var viewModel: ProductViewModel

    private lateinit var product: Product

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(ProductViewModel::class.java)

        arguments?.apply {
           product = getSerializable(PRODUCT) as Product
            viewModel.product = product
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_product_detail, container, false)
        dataBinding = FragmentProductDetailBinding.bind(rootView)
        dataBinding.viewModel = viewModel
        dataBinding.onClickListener = View.OnClickListener {
            viewModel.onReserve(product.id)
        }
        viewModel.showToast.observe(this, Observer {
            it?.apply {
                Toast.makeText(activity, this, Toast.LENGTH_LONG).show()
                activity?.finish()
            }
        })

        return dataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setToolbar()
        setCollapsingButtonIcon()
    }

    private fun setToolbar(){
        toolbar.title = ""
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24px)

        if(activity is AppCompatActivity){
            (activity as AppCompatActivity).setSupportActionBar(toolbar)
        }

    }

    private fun setCollapsingButtonIcon() {

        app_bar.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener {
            var isShow = false
            var scrollRange = -1

            override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.totalScrollRange
                }
                if (scrollRange + verticalOffset == 0) {
                    isShow = true
                    toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24px)
                } else if (isShow) {
                    isShow = false
                    toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24px)
                }
            }
        })
    }
}
