package com.lodjinha.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.lodjinha.R
import com.lodjinha.model.Products
import com.lodjinha.databinding.ItemProductBinding
import com.lodjinha.model.Product

class TopSellingListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var items: Products

    fun updateProducts(products: Products) {
        items = products
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
        (holder as TopSellingViewHolder).bind(items.data[position])

    override fun getItemCount(): Int = if (::items.isInitialized) items.data.size else 0

    class TopSellingViewHolder(private val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product) {
            binding.product = product
        }
    }
}
