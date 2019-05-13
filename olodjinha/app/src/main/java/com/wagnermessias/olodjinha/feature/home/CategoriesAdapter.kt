package com.wagnermessias.olodjinha.feature.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wagnermessias.olodjinha.R
import com.wagnermessias.olodjinha.core.model.Category

internal class CategoriesAdapter(private val categories: List<Category>)
    : RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.category_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = categories.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            description.text = categories[position].description
            image.apply {
                Glide.with(context)
                    .load(categories[position].urlImage)
                    .centerCrop()
                    .placeholder(R.drawable.ic_placeholder)
                    .into(this)
            }
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val description: TextView = view.findViewById(R.id.category_desc)
        val image: ImageView = view.findViewById(R.id.category_image)
    }

}
