package br.com.sf.lojinha.ui.products_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.sf.lojinha.R
import br.com.sf.lojinha.injection.PerFragment
import br.com.sf.lojinha.repository.Category
import br.com.sf.lojinha.repository.Product
import br.com.sf.lojinha.ui.base.DaggerFragment
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.Unbinder
import com.github.yasevich.endlessrecyclerview.EndlessRecyclerView
import javax.inject.Inject


@PerFragment
class ProductsFragment : DaggerFragment(), ProductsContract.View, EndlessRecyclerView.Pager {

    @BindView(R.id.listProducts)
    lateinit var listCategories: EndlessRecyclerView

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    @BindView(R.id.progressBar)
    lateinit var progressBar: ProgressBar

    @Inject
    internal lateinit var presenter: ProductsContract.Presenter

    private var unbinder: Unbinder? = null

    private lateinit var productsAdapter: ProductsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list_products, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        unbinder = ButterKnife.bind(this, view)

        productsAdapter = ProductsAdapter(presenter::clickProduct)

        val linearLayoutManager = LinearLayoutManager(activity)

        listCategories.setHasFixedSize(true)
        listCategories.layoutManager = linearLayoutManager
        listCategories.adapter = productsAdapter

        listCategories.setProgressView(R.layout.item_progress);
        listCategories.setPager(this);
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        arguments?.getParcelable<Category>(PARAM_1)?.run { presenter.loadProducts(this) }

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)
        toolbar.setNavigationOnClickListener { presenter.goToBack() }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        unbinder?.unbind()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.destroy()
    }

    override fun loadNextPage() {
        presenter.loadNextPage()
    }

    override fun shouldLoad(): Boolean {
        return presenter.shouldLoadNextPage()
    }

    override fun setTitle(title: String) {
        toolbar.title = title.toLowerCase()
    }

    override fun showProducts(products: List<Product>) {
        productsAdapter.addProducts(products)
    }

    override fun showLoadingIndicator(show: Boolean) {
//        if (show) {
//           s progressBar.visibility = View.VISIBLE
//        } else {
//        }
        listCategories.isRefreshing = show
        progressBar.visibility = View.GONE
    }

    companion object {
        const val PARAM_1 = "PARAM_1";

        fun newInstance(category: Category): Fragment {
            val bundle = Bundle()
            bundle.putParcelable(PARAM_1, category)

            return ProductsFragment().apply { arguments = bundle }
        }
    }
}
