package com.luizzabuscka.alodjinha.components.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.luizzabuscka.alodjinha.R
import com.luizzabuscka.commons.model.Product
import com.luizzabuscka.commons.model.State

class ProductsPagedAdapter(private val retry: () -> Unit) : PagedListAdapter<Product, RecyclerView.ViewHolder>(ProductDiffCallback) {

    private val DATA_VIEW_TYPE = 1
    private val FOOTER_VIEW_TYPE = 2

    private var state = State.LOADING

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == DATA_VIEW_TYPE) {
            ProductsAdapter.ProductViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_product,
                    parent,
                    false
                )
            )
        } else {
            FooterViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_footer,
                    parent,
                    false
                )
            )
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position < super.getItemCount()) DATA_VIEW_TYPE else FOOTER_VIEW_TYPE
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + if (hasFooter()) 1 else 0
    }

    private fun hasFooter(): Boolean {
        return super.getItemCount() != 0 && (state == State.LOADING || state == State.ERROR)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == DATA_VIEW_TYPE) {
            (holder as ProductsAdapter.ProductViewHolder).bind(getItem(position)!!)
        }
    }

    companion object {
        val ProductDiffCallback = object : DiffUtil.ItemCallback<Product>() {
            override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
                return oldItem == newItem
            }
        }
    }

    fun setState(state: State) {
        this.state = state
        notifyItemChanged(super.getItemCount())
    }

    class FooterViewHolder(view: View) : RecyclerView.ViewHolder(view)
}