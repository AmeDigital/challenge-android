package marco.challenge_android.presenter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_category.*
import marco.challenge_android.BuildConfig
import marco.challenge_android.R
import marco.challenge_android.data.Category
import marco.challenge_android.data.Product
import marco.challenge_android.presenter.category.CategoryAdapter
import marco.challenge_android.presenter.category.CategoryContract
import marco.challenge_android.presenter.category.CategoryEndlessRecyclerOnScrollListerner
import marco.challenge_android.presenter.category.CategoryPresenter

class CategoryActivity : BaseActivity(), CategoryContract.View {

    override lateinit var presenter: CategoryContract.Presenter
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var category: Category
    private var offset = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)
        initToolbar(R.id.toolbar_normal)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onResume() {
        super.onResume()
        category = intent.getSerializableExtra(EXTRA_CATEGORY) as Category
        initUI()
        addListeners()
    }

    override fun onPause() {
        super.onPause()
        presenter.unsubscribe()
    }

    override fun initUI() {
        title = category.description
        presenter = CategoryPresenter(this, category.id)
        presenter.subscribe()
        layoutManager = LinearLayoutManager(this)
        setupCategories()
    }

    override fun addListeners() {
        val onScrollListener = object : CategoryEndlessRecyclerOnScrollListerner(layoutManager) {
            override fun onLoadMore(current_page: Int) {
                offset += BuildConfig.LIMIT_OFFSET
                presenter.getProductsFromCategory(offset)
            }
        }
        products_list.addOnScrollListener(onScrollListener)
    }

    private fun setupCategories() {
        categoryAdapter = CategoryAdapter {
            startActivity(ProductDetailActivity.newInstance(this, it))
        }

        with(products_list) {
            itemAnimator = DefaultItemAnimator()
            this.layoutManager = layoutManager
            adapter = categoryAdapter
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
    }

    override fun setProducts(products: List<Product>?) {
        products.let {
            progress_products.visibility = View.GONE
            categoryAdapter.setItens(products, true)
        }
    }

    companion object {
        const val EXTRA_CATEGORY = "EXTRA_CATEGORY"
        fun newInstance(context: Context?, category: Category?): Intent {
            val intent = Intent(context, CategoryActivity::class.java)
            intent.putExtra(EXTRA_CATEGORY, category)
            return intent
        }

    }

}