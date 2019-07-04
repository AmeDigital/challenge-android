package br.com.igorfs.lodjinha.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.igorfs.lodjinha.R
import br.com.igorfs.lodjinha.vo.CategoryVo

class CategoryListAdapter:
        RecyclerView.Adapter<CategoryViewHolder>() {

    private var categoryList: ArrayList<CategoryVo> = arrayListOf()

    fun loadItems(newList: List<CategoryVo>){
        categoryList.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.category_item_layout, parent, false)
        return CategoryViewHolder(view)
    }

    override fun getItemCount() = categoryList.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val item = categoryList[position]
        holder.bindView(item)
    }
}