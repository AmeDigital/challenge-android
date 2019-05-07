package br.com.sf.lojinha.ui.product_details

import android.graphics.PorterDuff
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.core.content.res.ResourcesCompat
import androidx.core.text.HtmlCompat
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import br.com.sf.lojinha.R
import br.com.sf.lojinha.injection.PerFragment
import br.com.sf.lojinha.repository.Product
import br.com.sf.lojinha.ui.base.DaggerFragment
import br.com.sf.lojinha.ui.main.MainActivity
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import butterknife.Unbinder
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.picasso.Picasso
import javax.inject.Inject


@PerFragment
class ProductDetailFragment : DaggerFragment(), ProductDetailContract.View {

    @BindView(R.id.appBarLayout)
    internal lateinit var appBarLayout: AppBarLayout

    @BindView(R.id.collapsingToolbar)
    internal lateinit var collapsingToolbar: CollapsingToolbarLayout

    @BindView(R.id.toolbar)
    internal lateinit var toolbar: Toolbar

    @BindView(R.id.imProduct)
    internal lateinit var imProduct: ImageView

    @BindView(R.id.tvProductName)
    internal lateinit var tvProductName: TextView

    @BindView(R.id.tvPriceOf)
    internal lateinit var tvPriceOf: TextView

    @BindView(R.id.tvPriceBy)
    internal lateinit var tvPriceBy: TextView

    @BindView(R.id.tvCategoryName)
    internal lateinit var tvCategoryName: TextView

    @BindView(R.id.tvDescription)
    internal lateinit var tvDescription: TextView

    @BindView(R.id.btReserve)
    internal lateinit var btReserve: FloatingActionButton

    @Inject
    internal lateinit var presenter: ProductDetailContract.Presenter

    private var unbinder: Unbinder? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_product_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        unbinder = ButterKnife.bind(this, view)

        appBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { _, verticalOffset ->
            if (collapsingToolbar.height + verticalOffset < 2 * ViewCompat.getMinimumHeight(collapsingToolbar)) {
                toolbar.navigationIcon?.setColorFilter(
                    ResourcesCompat.getColor(resources, R.color.material_white, activity!!.theme),
                    PorterDuff.Mode.SRC_ATOP
                )
            } else {
                toolbar.navigationIcon?.setColorFilter(
                    ResourcesCompat.getColor(resources, R.color.material_black, activity!!.theme),
                    PorterDuff.Mode.SRC_ATOP
                )
            }
        })

        toolbar.setNavigationOnClickListener { presenter.goToBack() }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupAppBar()

        arguments?.getParcelable<Product>(PARAM_1)?.run { presenter.loadProduct(this) }
    }

    private fun setupAppBar() {
        (activity as MainActivity)
            .customAppBar
            .apply {
                setTitle(getString(R.string.application_name))
                clearMenu()
                setMenuRes(R.menu.menu_empty, R.menu.menu_empty, R.menu.menu_empty)
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        unbinder?.unbind()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.destroy()
    }

    override fun showProduct(product: Product) {
        toolbar.title = product.category.description

        tvProductName.text = product.name
        tvPriceOf.text = String.format("De %s", product.priceFrom)
        tvPriceBy.text = String.format("Por %s", product.priceBy)
        tvDescription.text = String.format("Por %s", product.description)
        tvCategoryName.text = product.category.description

        tvDescription.text = HtmlCompat.fromHtml(product.description, HtmlCompat.FROM_HTML_MODE_COMPACT)

        Picasso.get()
            .load(product.image)
            .noFade()
            .into(imProduct)

    }

    override fun disableReserveButton(disable: Boolean) {
        btReserve.isEnabled = !disable
    }

    override fun showProductReservedSuccessful() {
        val builder = AlertDialog.Builder(context!!)
        builder.setCancelable(false)
        builder.setMessage(getString(R.string.product_detail_reserve_successful))
        builder.setPositiveButton(getString(android.R.string.ok)) { dialog, which ->
            dialog.dismiss()
        }
        builder.create().show()
    }

    override fun showProductReserveFailed(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    override fun showProductReserveFailed() {
        Toast.makeText(activity, R.string.product_detail_reserve_failed, Toast.LENGTH_SHORT).show()
    }

    @OnClick(R.id.btReserve)
    fun onReserveClick() {
        presenter.clickReserve()
    }

    companion object {
        const val PARAM_1 = "PARAM_1"

        fun newInstance(product: Product): Fragment {
            val bundle = Bundle().apply {
                putParcelable(PARAM_1, product)
            }

            return ProductDetailFragment().apply { arguments = bundle }
        }
    }
}
