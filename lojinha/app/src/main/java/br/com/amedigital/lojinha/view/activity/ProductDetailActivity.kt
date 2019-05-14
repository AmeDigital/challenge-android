package br.com.amedigital.lojinha.view.activity

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.PorterDuff
import android.os.Build
import android.os.Bundle
import android.view.View
import br.com.amedigital.lojinha.R
import br.com.amedigital.lojinha.model.MaisVendido
import br.com.amedigital.lojinha.model.Produto
import com.br.cinesky.base.BaseActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_product_detail.*
import kotlinx.android.synthetic.main.activity_product_detail.fab
import kotlinx.android.synthetic.main.activity_product_detail_list.*
import java.util.*

class ProductDetailActivity : BaseActivity() {
    private lateinit var maisVendido: Produto

    companion object {
        private const val PRODUCT: String = "PRODUCT"

        fun getIntent(context: Context, produto: Produto): Intent {
            var intent = Intent(context, ProductDetailActivity::class.java)
            intent.putExtra(PRODUCT, produto)
            return intent
        }
    }

    override fun getRootLayoutId(): Int {
        return R.layout.activity_product_detail
    }

    override fun setupView(savedInstanceState: Bundle?) {
        intent.extras?.let {
            maisVendido = it.getSerializable(PRODUCT) as Produto
        }

        binds()
    }

    private fun binds() {
        tbProoductDetail.title = maisVendido.nome
        tbProoductDetail.setNavigationOnClickListener { onBackPressed() }
        if(toolbar != null){
            setSupportActionBar(toolbar)
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.setHomeButtonEnabled(true)
        }
        tvProductName.text = maisVendido.nome
        tvDescription.text = maisVendido.descricao
        rbPriceDe.text = getString(R.string.preco_de)+maisVendido.precoDe.toString()
        rbPricePor.text = getString(R.string.preco_por)+ maisVendido.precoPor.toString()
        setupToolbar()
        setupImageToolbar(maisVendido.urlImagem!!)

        fab.setOnClickListener {
            val builder = AlertDialog.Builder(this@ProductDetailActivity)

            builder.setMessage("Produto reservado com sucesso")

            builder.setPositiveButton("OK"){dialog, which ->

            }

            val dialog: AlertDialog = builder.create()

            dialog.show()
        }

    }

    private fun setupToolbar() {
        setSupportActionBar(tbProoductDetail)

        val upArrow = resources.getDrawable(R.drawable.ic_arrow_back_black_24dp).mutate()
        upArrow.setColorFilter(resources.getColor(R.color.colorWhite), PorterDuff.Mode.SRC_ATOP)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Objects.requireNonNull(supportActionBar)!!.setHomeAsUpIndicator(upArrow)
            Objects.requireNonNull(supportActionBar)!!.setDisplayHomeAsUpEnabled(true)
        }

        collapsingToolbar.setExpandedTitleColor(resources.getColor(R.color.colorWhite))
        collapsingToolbar.setCollapsedTitleTextColor(resources.getColor(R.color.colorWhite))
        collapsingToolbar.collapsedTitleGravity = View.TEXT_ALIGNMENT_GRAVITY
    }

    fun setupImageToolbar(imagePath: String) {
        Picasso.with(this)
            .load(imagePath)
            .into(ivMovieImage)
    }

}
