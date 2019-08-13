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

        Glide
            .with(holder.ivProduct.context)
            .load(items[position].urlImage)
            .placeholder(R.drawable.logo_menu)
            .centerCrop()
            .into(holder.ivProduct)

        holder.tvDescription.text = items[position].name
        holder.tvPriceFrom.text = holder.tvPriceFrom.context.getString(R.string.price_from, items[position].priceFrom)
        holder.tvPriceFrom.paintFlags = holder.tvPriceFrom.paintFlags or STRIKE_THRU_TEXT_FLAG
        holder.tvPrice.text = holder.tvPrice.context.getString(R.string.price_to, items[position].price)

        holder.itemView.setOnClickListener {
            it.context.startActivity<ProductActivity>("product" to items[position])
        }
    }


    class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivProduct = view.find<ImageView>(R.id.ivProduct)
        val tvDescription = view.find<TextView>(R.id.tvDescription)
        val tvPriceFrom = view.find<TextView>(R.id.tvPriceFrom)
        val tvPrice = view.find<TextView>(R.id.tvPrice)
    }
}