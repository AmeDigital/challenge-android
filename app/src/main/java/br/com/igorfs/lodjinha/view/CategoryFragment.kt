package br.com.igorfs.lodjinha.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.igorfs.lodjinha.R
import br.com.igorfs.lodjinha.adapter.ProductsAdapter
import br.com.igorfs.lodjinha.repository.ProductRepository
import kotlinx.android.synthetic.main.fragment_category.category_progressBar
import kotlinx.android.synthetic.main.fragment_category.category_recyclerview

class CategoryFragment : Fragment() {

    private val productRepository = ProductRepository()

    companion object {
        private const val CATEGORY_ID = "categoryId"
        operator fun invoke(categoryId: Long) = CategoryFragment().also {
            it.arguments = Bundle().apply {
                this.putLong(CATEGORY_ID, categoryId)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categoryArgs: CategoryFragmentArgs by navArgs()

        setupRecyclerView()
        setupCall(categoryArgs.categoryId)
    }

    private fun setupRecyclerView() {
        val productsAdapter = ProductsAdapter()

        category_recyclerview.adapter = productsAdapter
        category_recyclerview.layoutManager = LinearLayoutManager(requireContext())

        productRepository.getProductsList().observe(this, Observer {
            productsAdapter.loadItems(it?: emptyList())
            hideLoading()
        })
    }

    private fun setupCall(categoryId: Long) {
        productRepository.fetchProductsData(categoryId = categoryId)

        //TODO setup page loading scroll
    }

    private fun hideLoading() {
        category_progressBar.visibility = View.GONE
        category_recyclerview.visibility = View.VISIBLE
    }
}
