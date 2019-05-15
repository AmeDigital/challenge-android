package br.com.amedigital.lojinha.view.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import br.com.amedigital.lojinha.R
import br.com.amedigital.lojinha.extension.setDefaultVerticalSettings
import br.com.amedigital.lojinha.model.Categoria
import br.com.amedigital.lojinha.model.ProdutoResponse
import br.com.amedigital.lojinha.view.adapter.ProductListAdapter
import br.com.amedigital.lojinha.viewmodel.ProductListViewModel
import com.br.cinesky.base.BaseActivity
import kotlinx.android.synthetic.main.activity_product_detail_list.*

class ProductDetailListActivity : BaseActivity() {
    private lateinit var categoria: Categoria
    private lateinit var viewModel: ProductListViewModel

    companion object {
        private const val CATEGORIA: String = "CATEGORIA"
        fun getIntent(context: Context, categoria: Categoria): Intent {
            var intent = Intent(context, ProductDetailListActivity::class.java)
            intent.putExtra(CATEGORIA, categoria)
            return intent
        }
    }

    override fun getRootLayoutId(): Int {
        return R.layout.activity_product_detail_list
    }


    override fun setupView(savedInstanceState: Bundle?) {
        intent.extras?.let {
            categoria = it.getSerializable(CATEGORIA) as Categoria
        }
        initializeViewModel()
        binds()
        open()
    }

    private fun binds() {
        toolbar.title = categoria.descricao
        if (toolbar != null) {
            setSupportActionBar(toolbar)
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }

    }

    private fun initializeViewModel() {
        if (!this::viewModel.isInitialized) {
            viewModel = ViewModelProviders.of(this).get(ProductListViewModel::class.java)
            setObserveLive(viewModel)
        }
    }

    private fun open() {
        val listProductObservable = Observer<ProdutoResponse> {
            if (rclItensDetail != null) {
                titleEmpty.visibility = View.GONE
                rclItensDetail.visibility = View.VISIBLE
                var bestSellerAdapter = ProductListAdapter(it as ProdutoResponse, this)
                rclItensDetail.setDefaultVerticalSettings(this, bestSellerAdapter)
            } else {
                titleEmpty.visibility = View.VISIBLE
                rclItensDetail.visibility = View.GONE
            }

        }
        viewModel.productListRepositoryResponse().observe(this, listProductObservable)
        viewModel.loadRepositories(categoria.id.toString())
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            android.R.id.home -> finish() // Finaliza a Activity atual e assim volta para a tela anterior
            else -> {
            }
        }
        return true
    }
}
