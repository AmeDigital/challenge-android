package com.luizzabuscka.alodjinha.ui.home.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.luizzabuscka.alodjinha.R
import com.luizzabuscka.alodjinha.ui.category.CategoryActivity
import com.luizzabuscka.commons.models.Category
import kotlinx.android.synthetic.main.item_category.view.*
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity

class CategoriesAdapter(private val items: List<Category>) : RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.tvCategory.text = items[position].description

        Glide
            .with(holder.ivCategory.context)
            .load(items[position].urlImage)
            .placeholder(R.drawable.logo_menu)
            .centerCrop()
            .into(holder.ivCategory)

        holder.itemView.setOnClickListener {
            it.context.startActivity<CategoryActivity>("category" to items[position])
        }
    }

    class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivCategory = view.find<ImageView>(R.id.ivCategory)
        val tvCategory = view.find<TextView>(R.id.tvCategory)
    }
}