package com.luizzabuscka.alodjinha.components.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.luizzabuscka.alodjinha.R
import com.luizzabuscka.commons.model.Product
import org.jetbrains.anko.find
import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import com.luizzabuscka.alodjinha.ui.product.ProductActivity
import org.jetbrains.anko.startActivity


class ProductsAdapter(private val items: List<Product>) : RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(items[position])
    }


    class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val ivProduct = view.find<ImageView>(R.id.ivProduct)
        private val tvDescription = view.find<TextView>(R.id.tvDescription)
        private val tvPriceFrom = view.find<TextView>(R.id.tvPriceFrom)
        private val tvPrice = view.find<TextView>(R.id.tvPrice)

        fun bind(product: Product) {
            Glide
                .with(ivProduct.context)
                .load(product.urlImage)
                .placeholder(R.drawable.placeholder)
                .centerCrop()
                .into(ivProduct)

            tvDescription.text = product.name
            tvPriceFrom.text = tvPriceFrom.context.getString(R.string.price_from, product.priceFrom)
            tvPriceFrom.paintFlags = tvPriceFrom.paintFlags or STRIKE_THRU_TEXT_FLAG
            tvPrice.text = tvPrice.context.getString(R.string.price_to, product.price)

            itemView.setOnClickListener {
                it.context.startActivity<ProductActivity>("product" to product)
            }
        }
    }
}