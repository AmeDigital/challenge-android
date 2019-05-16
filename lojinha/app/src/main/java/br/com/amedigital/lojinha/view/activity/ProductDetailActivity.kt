package br.com.amedigital.lojinha.view.activity

import android.app.AlertDialog
import android.arch.lifecycle.ViewModelProviders
import android.arch.lifecycle.Observer
import android.content.Context
import android.content.Intent
import android.graphics.PorterDuff
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import br.com.amedigital.lojinha.api.response.ResultResponse
import br.com.amedigital.lojinha.extension.setDefaultVerticalSettings
import br.com.amedigital.lojinha.model.Produto
import br.com.amedigital.lojinha.model.ProdutoResponse
import br.com.amedigital.lojinha.view.adapter.ProductListAdapter
import br.com.amedigital.lojinha.viewmodel.ProductListViewModel
import com.br.cinesky.base.BaseActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_product_detail.*
import kotlinx.android.synthetic.main.activity_product_detail_list.*
import java.util.*


class ProductDetailActivity : BaseActivity() {
    private lateinit var maisVendido: Produto
    private lateinit var viewModel: ProductListViewModel

    companion object {
        private const val PRODUCT: String = "PRODUCT"

        fun getIntent(context: Context, produto: Produto): Intent {
            var intent = Intent(context, ProductDetailActivity::class.java)
            intent.putExtra(PRODUCT, produto)
            return intent
        }
    }

    override fun getRootLayoutId(): Int {
        return br.com.amedigital.lojinha.R.layout.activity_product_detail
    }

    override fun setupView(savedInstanceState: Bundle?) {
        intent.extras?.let {
            maisVendido = it.getSerializable(PRODUCT) as Produto
        }
        initializeViewModel()
        binds()
    }

    private fun initializeViewModel() {
        if (!this::viewModel.isInitialized) {
            viewModel = ViewModelProviders.of(this).get(ProductListViewModel::class.java)
            setObserveLive(viewModel)
        }
    }

    private fun binds() {
        tbProoductDetail.title = maisVendido.nome
        tbProoductDetail.setNavigationOnClickListener { onBackPressed() }
        if (toolbar != null) {
            setSupportActionBar(toolbar)
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }
        tvProductName.text = maisVendido.nome
        tvDescription.text = maisVendido.descricao
        rbPriceDe.text = getString(br.com.amedigital.lojinha.R.string.preco_de) + maisVendido.precoDe.toString()
        rbPricePor.text = getString(br.com.amedigital.lojinha.R.string.preco_por) + maisVendido.precoPor.toString()
        setupToolbar()
        setupImageToolbar(maisVendido.urlImagem!!)

        fab.setOnClickListener {
            open()
        }

    }

    private fun open() {
        val listProductObservable = Observer<ResultResponse> {
            val builder = AlertDialog.Builder(this@ProductDetailActivity)
            builder.setMessage("Produto reservado com sucesso")
            builder.setPositiveButton("OK") { dialog, which ->
                dialog.dismiss()
            }

            val dialog: AlertDialog = builder.create()

            dialog.show()
        }
        viewModel.productRepositoryResponse().observe(this, listProductObservable)
        viewModel.setRepositories(maisVendido.id.toString())

    }


    private fun setupToolbar() {
        setSupportActionBar(tbProoductDetail)

        val upArrow = resources.getDrawable(br.com.amedigital.lojinha.R.drawable.ic_arrow_back_black_24dp).mutate()
        upArrow.setColorFilter(
            resources.getColor(br.com.amedigital.lojinha.R.color.colorPrimaryDark),
            PorterDuff.Mode.SRC_ATOP
        )
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            supportActionBar!!.setHomeAsUpIndicator(upArrow)
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }

        collapsingToolbar.setExpandedTitleColor(resources.getColor(br.com.amedigital.lojinha.R.color.colorWhite))
        collapsingToolbar.setCollapsedTitleTextColor(resources.getColor(br.com.amedigital.lojinha.R.color.colorWhite))
        collapsingToolbar.collapsedTitleGravity = View.TEXT_ALIGNMENT_GRAVITY
    }

    private fun setupImageToolbar(imagePath: String) {
        Picasso.with(this)
            .load(imagePath)
            .into(ivMovieImage)
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


