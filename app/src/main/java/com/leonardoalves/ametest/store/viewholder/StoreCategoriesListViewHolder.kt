package com.leonardoalves.ametest.store.viewholder

import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.leonardoalves.ametest.R
import com.leonardoalves.ametest.custom.RecyclerViewAdapter
import com.leonardoalves.ametest.custom.ViewHolder
import com.leonardoalves.ametest.custom.ViewHolderFactory
import com.leonardoalves.ametest.custom.ViewModel
import com.leonardoalves.ametest.store.category.CATEGORY_SERIALIZABLE_EXTRA
import com.leonardoalves.ametest.store.viewmodel.StoreCategoriesListViewModel
import com.leonardoalves.ametest.store.viewmodel.StoreCategoryViewModel
import kotlinx.android.synthetic.main.view_store_categories.view.*
import kotlinx.android.synthetic.main.view_store_category.view.*
import java.lang.IllegalArgumentException

const val STORE_CATEGORIES_LIST_VIEW_ID = R.layout.view_store_categories
const val STORE_CATEGORY_VIEW_ID = R.layout.view_store_category

class StoreCategoriesListViewHolder(itemView: View, val listener: Listener<StoreCategoryViewModel>) : ViewHolder<StoreCategoriesListViewModel>(itemView) {

    private val viewPool = RecyclerView.RecycledViewPool()

    override fun bind(viewModel: StoreCategoriesListViewModel) {
        val viewAdapter = RecyclerViewAdapter(object : ViewHolderFactory {
            override fun getType(viewModel: ViewModel): Int = when (viewModel) {
                is StoreCategoryViewModel -> STORE_CATEGORY_VIEW_ID
                else -> throw IllegalArgumentException()
            }

            override fun getHolder(viewType: Int, view: View): ViewHolder<*> = when (viewType) {
                STORE_CATEGORY_VIEW_ID -> StoreCategoryViewHolder(view, listener)
                else -> throw IllegalArgumentException()
            }
        })
        with(itemView) {
            rvCategories.apply {
                layoutManager = LinearLayoutManager(this@with.context, RecyclerView.HORIZONTAL, false)
                adapter = viewAdapter
                setRecycledViewPool(viewPool)
            }
        }
        viewAdapter.setItems(viewModel.categories)
    }

    override fun recycle() {
        viewPool.clear()
    }

}

class StoreCategoryViewHolder(itemView: View, val listener: Listener<StoreCategoryViewModel>) : ViewHolder<StoreCategoryViewModel>(itemView) {
    override fun bind(viewModel: StoreCategoryViewModel) {
        with(itemView) {
            tvCategoryTitle.text = viewModel.description
            Glide.with(context)
                .load(viewModel.image)
                .into(ivCategoryImage)
            setOnClickListener {
                listener.onClick(viewModel)
            }
        }
    }

    override fun recycle() {
        Glide.with(itemView.context)
            .clear(itemView.ivCategoryImage)
    }

}