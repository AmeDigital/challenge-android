package com.ame.lojinhatesteame.ui.product

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.ame.lojinhatesteame.BR
import com.ame.lojinhatesteame.R
import com.ame.lojinhatesteame.data.model.Product
import com.ame.lojinhatesteame.util.AdapterItemsContract


class ProductAdapter (var products: List<Product>, private val callback: ((Product) -> Unit)?) : RecyclerView.Adapter<ProductAdapter.ViewHolder>(),
    AdapterItemsContract {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ViewDataBinding = DataBindingUtil.inflate(layoutInflater, R.layout.product_item, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
       return products.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(products[position], callback)
    }

    override fun replaceItems(list: List<*>) {
        this.products = list as List<Product>
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product, callback: ((Product) -> Unit)?){
            binding.setVariable(BR.product, product)
            binding.root.setOnClickListener {
                callback?.invoke(product)
            }
        }
    }
}

