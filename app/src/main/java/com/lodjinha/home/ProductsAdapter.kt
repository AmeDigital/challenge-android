package com.lodjinha.home

import android.content.Intent
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.lodjinha.R
import com.lodjinha.model.Products
import com.lodjinha.databinding.ItemProductBinding
import com.lodjinha.detailProduct.DetailProductActivity
import com.lodjinha.detailProduct.DetailProductActivity.Companion.DETAIL_PRODUCT_EXTRA
import com.lodjinha.model.Product

class ProductsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items = mutableListOf<Product>()

    fun updateProducts(products: Products) {
        items.clear()
        items.addAll(products.data)
        notifyDataSetChanged()
    }

    fun addProducts(products: Products) {
        items.addAll(products.data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder = TopSellingViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_product,
            parent,
            false
        ) as ItemProductBinding
    )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        (holder as TopSellingViewHolder).bind(items[position])

    override fun getItemCount(): Int = items.size

    class TopSellingViewHolder(private val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(newProduct: Product) = with(binding) {
            product = newProduct
            priceArea.originalPriceProduct.paintFlags =
                priceArea.originalPriceProduct.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG

            root.setOnClickListener {
                it.context.startActivity(
                    Intent(it.context, DetailProductActivity::class.java)
                        .putExtra(DETAIL_PRODUCT_EXTRA, Gson().toJson(newProduct))
                )
            }
        }
    }
}
