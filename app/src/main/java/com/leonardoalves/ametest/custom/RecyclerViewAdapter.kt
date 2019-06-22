package com.leonardoalves.ametest.custom

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

interface ViewModel

interface ViewHolderFactory {
    fun getType(viewModel: ViewModel): Int
    fun getHolder(viewType: Int, view: View): ViewHolder<*>
}

abstract class ViewHolder<in T : ViewModel>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    interface Listener<T> {
        fun onClick(viewModel: T)
    }

    abstract fun bind(viewModel: T)
    abstract fun recycle()
}

class RecyclerViewAdapter(private val viewHolderFactory: ViewHolderFactory) : RecyclerView.Adapter<ViewHolder<ViewModel>>() {

    private var recyclerView: RecyclerView? = null

    private val items = arrayListOf<ViewModel>()

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        this.recyclerView = recyclerView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<ViewModel> {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return viewHolderFactory.getHolder(viewType, view) as ViewHolder<ViewModel>
    }

    override fun onBindViewHolder(holder: ViewHolder<ViewModel>, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int) = viewHolderFactory.getType(items[position])

    fun setItems(items: List<ViewModel>) {
        with(this@RecyclerViewAdapter.items) {
            clear()
            addAll(items)
        }
        notifyDataSetChanged()
    }

    fun addItems(items: List<ViewModel>) {
        val firstInsertionPosition = this@RecyclerViewAdapter.items.size + 1
        this@RecyclerViewAdapter.items.addAll(items)
        notifyItemRangeInserted(firstInsertionPosition, this@RecyclerViewAdapter.items.size)
    }

    override fun onViewRecycled(holder: ViewHolder<ViewModel>) {
        holder.recycle()
    }

    fun getItemByPosition(position: Int) = items[position]

    fun getItem(viewModel: ViewModel): ViewModel? {
        val index = items.indexOf(viewModel)
        if (index < 0 )return null
        return items[index]
    }

    fun <R : ViewModel> getItemFromType(kClass: Class<R>): List<R> {
        return items.filterIsInstance(kClass)
    }

}