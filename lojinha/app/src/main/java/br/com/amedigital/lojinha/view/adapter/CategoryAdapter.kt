package br.com.amedigital.lojinha.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import br.com.amedigital.lojinha.R
import br.com.amedigital.lojinha.model.Categoria
import br.com.amedigital.lojinha.model.CategoriaResponse
import br.com.amedigital.lojinha.view.HomeFragment
import com.squareup.picasso.Picasso

class CategoryAdapter(
    private var categoryList: CategoriaResponse,
    private var homeFragment: HomeFragment
) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.category_item_layout, viewGroup, false)
        return ViewHolder(view)
    }

    interface Callbacks {
        fun open(categoria: Categoria, position: Int)
    }

    override fun getItemCount(): Int {
        return categoryList.data!!.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        var categorys: Categoria = categoryList.data!![position]
        var context: Context = viewHolder.view.context

        viewHolder.setData(categorys)

        Picasso.with(context)
            .load(categorys.urlImagem)
            .error(R.drawable.blank)
            .into(viewHolder.thumbNail)

        viewHolder.view.setOnClickListener {


        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var view: View = view
        var thumbNail: ImageView = view.findViewById(R.id.thumbnail)
        var description: TextView = view.findViewById(R.id.description)
        fun setData(category: Categoria) {
            description.text = category.descricao
        }
    }
}