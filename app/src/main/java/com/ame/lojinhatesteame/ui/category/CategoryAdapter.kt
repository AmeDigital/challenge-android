package com.ame.lojinhatesteame.ui.category

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.ame.lojinhatesteame.BR
import com.ame.lojinhatesteame.R
import com.ame.lojinhatesteame.data.model.Category
import com.ame.lojinhatesteame.util.AdapterItemsContract


class CategoryAdapter (var categories: List<Category>, private val callback: ((Category) -> Unit)?) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>(),
    AdapterItemsContract {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ViewDataBinding = DataBindingUtil.inflate(layoutInflater, R.layout.category_item, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
       return categories.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(categories[position], callback)
    }

    override fun replaceItems(list: List<*>) {
        this.categories = list as List<Category>
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(category: Category, callback: ((Category) -> Unit)?){
            binding.setVariable(BR.category, category)
            binding.root.setOnClickListener {
                callback?.invoke(category)
            }
        }
    }
}

