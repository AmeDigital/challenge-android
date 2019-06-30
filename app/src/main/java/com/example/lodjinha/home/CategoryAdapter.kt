package com.example.lodjinha.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.Category
import com.example.lodjinha.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.category_item.view.*


class CategoryAdapter(val onclickListener: (Int) -> Unit) : RecyclerView.Adapter<CategoryAdapter.CategoryVH>() {

    var listCategories : List<Category> = listOf()

    fun populateCategories(list: List<Category>){
        listCategories = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.category_item, parent, false)
        return CategoryVH(view)
    }

    override fun getItemCount(): Int {
        return listCategories.size
    }

    override fun onBindViewHolder(holder: CategoryVH, position: Int) {
        holder.bind(listCategories[position])
    }

    inner class CategoryVH(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: Category) {
            itemView?.apply {
                Picasso.get().load(item.imageUrl).into(ivCategory, object : Callback {
                    override fun onSuccess() {}
                    override fun onError(e: Exception) {
                        ivCategory?.setImageResource(R.drawable.default_image)
                    }
                })
                tvCategory?.text = item.description
                setOnClickListener {
                    onclickListener(item.id)
                }
            }
        }
    }

}