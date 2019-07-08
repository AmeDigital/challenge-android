package br.com.igorfs.lodjinha.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import br.com.igorfs.lodjinha.R
import br.com.igorfs.lodjinha.util.getLoaderPlaceholder
import br.com.igorfs.lodjinha.vo.ProductVo
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.top_sellers_item_layout.view.top_sellers_imageview
import kotlinx.android.synthetic.main.top_sellers_item_layout.view.top_sellers_item_title
import kotlinx.android.synthetic.main.top_sellers_item_layout.view.top_sellers_precoDe
import kotlinx.android.synthetic.main.top_sellers_item_layout.view.top_sellers_precoPor

class TopSellersViewHolder (itemView: View) :
    RecyclerView.ViewHolder(itemView) {
    private val imageView = itemView.top_sellers_imageview
    private val titleTextView = itemView.top_sellers_item_title
    private val precoDeTextView = itemView.top_sellers_precoDe
    private val precoPorTextView = itemView.top_sellers_precoPor

    fun bindView(item: ProductVo) {
        titleTextView.text = item.nome
        precoDeTextView.text = item.precoDe.toString()
        precoPorTextView.text = item.precoPor.toString()

        Glide
            .with(itemView)
            .load(item.urlImagem)
            .placeholder(getLoaderPlaceholder(itemView.context))
            .error(R.drawable.ic_placeholder)
            .into(imageView)

        itemView.setOnClickListener {
            //TODO Should open product description
        }
    }
}