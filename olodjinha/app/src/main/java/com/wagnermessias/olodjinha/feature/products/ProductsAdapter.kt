package com.wagnermessias.olodjinha.feature.products

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wagnermessias.olodjinha.R
import com.wagnermessias.olodjinha.core.extensions.toCurrency
import com.wagnermessias.olodjinha.core.model.Product

internal class ProductsAdapter(val products: ArrayList<Product>, val context: Context) :
    RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.product_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = products.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            image.apply {
                Glide.with(context)
                    .load(products[position].urlImage)
                    .centerCrop()
                    .placeholder(R.drawable.ic_placeholder)
                    .into(this)
            }
            name.text = products[position].name
            val priceformated = String.format(
                context.getResources().getString(R.string.price_from),
                products[position].priceFrom.toCurrency()
            )
            priceFrom.text = HtmlCompat.fromHtml(priceformated, HtmlCompat.FROM_HTML_MODE_LEGACY)
            pricePer.text = String.format(
                context.getResources().getString(R.string.price_per),
                products[position].pricePer.toCurrency()
            )
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.product_name)
        val image: ImageView = view.findViewById(R.id.product_image)
        val priceFrom: TextView = view.findViewById(R.id.product_price_from)
        val pricePer: TextView = view.findViewById(R.id.product_price_per)

    }

}
