package br.com.igorfs.lodjinha.adapter

import android.view.View
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import br.com.igorfs.lodjinha.R
import br.com.igorfs.lodjinha.util.getLoaderPlaceholder
import br.com.igorfs.lodjinha.view.HomeFragmentDirections
import br.com.igorfs.lodjinha.vo.CategoryVo
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.category_item_layout.view.description_category_item
import kotlinx.android.synthetic.main.category_item_layout.view.imageview_category_item

class CategoryViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
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
            val action = HomeFragmentDirections.actionHomeToCategory(item.id)
            itemView.findNavController().navigate(action)
        }
    }
}