package marco.challenge_android.presenter

import android.content.Context
import android.content.Intent
import android.graphics.PorterDuff
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v7.app.AlertDialog
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_product_detail.*
import marco.challenge_android.R
import marco.challenge_android.Utils
import marco.challenge_android.data.Product
import marco.challenge_android.presenter.product.ProductDetailContract
import marco.challenge_android.presenter.product.ProductDetailPresenter

class ProductDetailActivity : BaseActivity(), ProductDetailContract.View {

    override lateinit var presenter: ProductDetailContract.Presenter
    private lateinit var product: Product

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)
        initToolbar(R.id.toolbar_normal)
        with(supportActionBar) {
            this?.setDisplayHomeAsUpEnabled(true)
            this?.setDisplayShowHomeEnabled(true)
            this?.setDisplayShowTitleEnabled(true)
        }
        with(collapsing_toolbar) {
            setExpandedTitleColor(resources.getColor(android.R.color.transparent))
            setCollapsedTitleTextColor(resources.getColor(android.R.color.white))
        }
    }

    override fun onResume() {
        super.onResume()
        product = intent.getSerializableExtra(EXTRA_PRODUCT) as Product
        initUI()
        addListeners()
    }

    override fun onPause() {
        super.onPause()
        presenter.unsubscribe()
    }

    override fun initUI() {
        presenter = ProductDetailPresenter(this)
        product.let { title = product.category?.description

            Utils.loadImage(this, product.urlImage, img_product)
            txt_product_name.text = it.name
            txt_price_from.text = String.format(
                getString(R.string.price_from),
                Utils.formatCurrencyNew(product.priceFrom)
            )
            txt_price_to.text = String.format(
                getString(R.string.price_to),
                Utils.formatCurrencyNew(product.priceTo)
            )
            txt_product_description.text = Utils.formatHTML(product.description)
        }
    }

    override fun addListeners() {
        app_bar_layout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { _, verticalOffset ->
            val icon = toolbar.navigationIcon
            val color = if (verticalOffset == 0) resources.getColor(R.color.colorPrimary)
            else resources.getColor(android.R.color.white)

            icon!!.setColorFilter(color, PorterDuff.Mode.SRC_ATOP)
        })

        fab_reserve.setOnClickListener {
            presenter.reserveProduct(product.id)
            fab_progress_circle.show()
        }
    }

    override fun reserveProductStatus(status: Boolean) {
        fab_progress_circle.beginFinalAnimation()
        val message = if (status) getString(R.string.reserved_product)
        else getString(R.string.reserved_product_error)
        val dialog = AlertDialog.Builder(this)
        dialog.setMessage(message)
        dialog.setCancelable(false)
        dialog.setPositiveButton(getString(R.string.OK)) { d, _ ->
            d.dismiss()
            if (status) finish()
        }
        dialog.show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val EXTRA_PRODUCT = "EXTRA_PRODUCT"
        fun newInstance(context: Context, product: Product?): Intent {
            val intent = Intent(context, ProductDetailActivity::class.java)
            intent.putExtra(EXTRA_PRODUCT, product)
            return intent
        }
    }

}