package br.com.igorfs.lodjinha.adapter

import android.graphics.Paint
import android.view.View
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import br.com.igorfs.lodjinha.MainNavGraphDirections
import br.com.igorfs.lodjinha.R
import br.com.igorfs.lodjinha.util.getLoaderPlaceholder
import br.com.igorfs.lodjinha.vo.ProductVo
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.product_list_item_layout.view.product_item_imageview
import kotlinx.android.synthetic.main.product_list_item_layout.view.product_item_precoDe
import kotlinx.android.synthetic.main.product_list_item_layout.view.product_item_precoPor
import kotlinx.android.synthetic.main.product_list_item_layout.view.product_item_title

class ProductsViewHolder (itemView: View) :
    RecyclerView.ViewHolder(itemView) {
    private val imageView = itemView.product_item_imageview
    private val titleTextView = itemView.product_item_title
    private val precoDeTextView = itemView.product_item_precoDe
    private val precoPorTextView = itemView.product_item_precoPor

    fun bindView(product: ProductVo) {
        titleTextView.text = product.nome

        precoDeTextView.text = itemView.context.getString(R.string.preco_de, product.precoDe.toString())
        precoDeTextView.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        precoPorTextView.text = itemView.context.getString(R.string.preco_por, product.precoPor.toString())

        Glide
            .with(itemView)
            .load(product.urlImagem)
            .placeholder(getLoaderPlaceholder(itemView.context))
            .error(R.drawable.ic_placeholder)
            .into(imageView)

        itemView.setOnClickListener {
            val action = MainNavGraphDirections.actionToProduct(product)
            itemView.findNavController().navigate(action)
        }
    }
}