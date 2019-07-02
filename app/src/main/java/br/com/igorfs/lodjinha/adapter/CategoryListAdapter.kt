package br.com.igorfs.lodjinha.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.igorfs.lodjinha.R
import br.com.igorfs.lodjinha.util.getLoaderPlaceholder
import br.com.igorfs.lodjinha.vo.CategoryVo
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.category_item_layout.view.*

class CategoryListAdapter:
        RecyclerView.Adapter<CategoryListAdapter.CategoryViewHolder>() {

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


    inner class CategoryViewHolder(itemView: View) :
            RecyclerView.ViewHolder(itemView) {
        private val categoryDescription = itemView.description_category_item
        private val imageView = itemView.imageview_category_item

        fun bindView(item: CategoryVo){
            Glide
                .with(itemView)
                .load(item.urlImagem)
                .placeholder(getLoaderPlaceholder(itemView.context))
                .error(R.drawable.ic_placeholder)
                .into(imageView)

            categoryDescription.text = item.descricao

            itemView.setOnClickListener {
                TODO() // Open Category by id activity
            }
        }
    }
}