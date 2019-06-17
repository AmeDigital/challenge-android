package marco.challenge_android.presenter.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_home.*
import marco.challenge_android.R
import marco.challenge_android.data.Banner
import marco.challenge_android.data.Category
import marco.challenge_android.data.Product
import marco.challenge_android.presenter.CategoryActivity
import marco.challenge_android.presenter.ProductDetailActivity
import marco.challenge_android.presenter.category.CategoryAdapter
import java.util.*

class HomeFragment : Fragment(), HomeContract.View {

    override lateinit var presenter: HomeContract.Presenter
    private lateinit var bannerAdapter : HomeBannerPagerAdapter
    private lateinit var categoriesAdapter: HomeCategoriesAdapter
    private lateinit var bestSellerAdapter: CategoryAdapter
    private lateinit var timer : Timer
    var currentBanner = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        addListeners()
    }

    override fun onPause() {
        super.onPause()
        timer.cancel()
        presenter.unsubscribe()
    }

    override fun initUI() {
        presenter = HomePresenter(this)
        presenter.subscribe()
        setupBanners()
        setupCategories()
        setupBestSellers()
    }

    override fun addListeners() {
        pager_banner.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                currentBanner = position
            }
            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageSelected(position: Int) {}
        })
    }

    private fun setupBanners() {
        bannerAdapter = HomeBannerPagerAdapter(context) {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(it)))
        }
        pager_banner.adapter = bannerAdapter
        banner_indicator.setViewPager(pager_banner)
        bannerAdapter.registerDataSetObserver(banner_indicator.dataSetObserver)

        setupIndicator()
    }

    private fun setupCategories() {
        categoriesAdapter = HomeCategoriesAdapter {
            startActivity(CategoryActivity.newInstance(requireActivity(), it))
        }
        with(categories_list) {
            itemAnimator = DefaultItemAnimator()
            this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = categoriesAdapter
        }
    }

    private fun setupBestSellers() {
        bestSellerAdapter = CategoryAdapter {
            startActivity(ProductDetailActivity.newInstance(requireActivity(), it))
        }
        with(best_sellers_list) {
            itemAnimator = DefaultItemAnimator()
            this.layoutManager = LinearLayoutManager(context)
            adapter = bestSellerAdapter
        }
    }

    override fun setBanners(banners: List<Banner>) {
        progress_banner.visibility = View.GONE
        bannerAdapter.setBanners(banners)
    }

    override fun setCategories(categories: List<Category>) {
        progress_categories.visibility = View.GONE
        categoriesAdapter.setCategories(categories)
    }

    override fun setBestSellers(bestSellers: List<Product>) {
        progress_best_sellers.visibility = View.GONE
        bestSellerAdapter.setItens(bestSellers)
    }

    private fun setupIndicator() {
        val handler = Handler()
        val timerTask = object: TimerTask() {
            override fun run() {
                handler.post {
                    if (currentBanner == pager_banner.adapter?.count) currentBanner = 0
                    pager_banner.setCurrentItem(currentBanner++, true)
                }
            }
        }
        timer = Timer()
        timer.schedule(timerTask, 3000, 3000)
    }

}