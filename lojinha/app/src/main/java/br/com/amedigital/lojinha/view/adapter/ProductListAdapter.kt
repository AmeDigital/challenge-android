package br.com.amedigital.lojinha.view.adapter

import android.content.Context
import android.os.Build
import android.support.v4.app.ActivityOptionsCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import br.com.amedigital.lojinha.R
import br.com.amedigital.lojinha.model.Categoria
import br.com.amedigital.lojinha.model.Produto
import br.com.amedigital.lojinha.model.ProdutoResponse
import br.com.amedigital.lojinha.view.activity.ProductDetailActivity
import br.com.amedigital.lojinha.view.activity.ProductDetailListActivity
import com.squareup.picasso.Picasso

class ProductListAdapter(
    private var categoryList: ProdutoResponse,
    var productDetailListActivity: ProductDetailListActivity
) : RecyclerView.Adapter<ProductListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.best_sellers_item_layout, viewGroup, false)
        return ViewHolder(view)
    }

    interface Callbacks {
        fun open(categoria: Categoria, position: Int)
    }

    override fun getItemCount(): Int {
        return categoryList.data!!.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        var produto: Produto = categoryList.data!![position]
        var context: Context = viewHolder.view.context

        viewHolder.setData(produto)
        viewHolder.precoDe.text = context.getString(R.string.preco_de)+produto.precoDe.toString()
        viewHolder.precoPor.text = context.getString(R.string.preco_por)+ produto.precoPor.toString()

        Picasso.with(context)
            .load(produto.urlImagem)
            .error(R.drawable.blank)
            .into(viewHolder.thumbNail)

        viewHolder.view.setOnClickListener {

            val intent = ProductDetailActivity.getIntent(context, produto)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    productDetailListActivity!!, viewHolder.thumbNail, "root")
                productDetailListActivity!!.startActivity(intent, options.toBundle())
            }else{
                productDetailListActivity!!.startActivity(intent)
            }

        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var view: View = view
        var thumbNail: ImageView = view.findViewById(R.id.thumbnail)
        var nomeProduto: TextView = view.findViewById(R.id.tvProductName)
        var precoDe: TextView = view.findViewById(R.id.rbPriceDe)
        var precoPor: TextView = view.findViewById(R.id.rbPricePor)
        fun setData(maisVendido: Produto) {
            nomeProduto.text = maisVendido.nome +" "+ maisVendido.descricao

        }
    }
}