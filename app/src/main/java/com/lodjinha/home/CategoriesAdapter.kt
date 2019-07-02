package com.lodjinha.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.lodjinha.R
import com.lodjinha.category.CategoryActivity
import com.lodjinha.category.CategoryActivity.Companion.CATEGORY_EXTRA
import com.lodjinha.model.Categories
import com.lodjinha.databinding.ItemCategoryBinding
import com.lodjinha.model.Category

class CategoriesAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var items: Categories

    fun updateCategories(categories: Categories) {
        items = categories
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder = CategoriesViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_category,
            parent,
            false
        ) as ItemCategoryBinding
    )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        (holder as CategoriesViewHolder).bind(items.data[position])

    override fun getItemCount(): Int = if (::items.isInitialized) items.data.size else 0

    class CategoriesViewHolder(private val binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(category: Category) {
            binding.category = category

            binding.root.setOnClickListener {
                it.context.startActivity(
                    Intent(it.context, CategoryActivity::class.java).putExtra( CATEGORY_EXTRA, Gson().toJson(category))
                )
            }
        }
    }
}
