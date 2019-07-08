package br.com.igorfs.lodjinha.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.igorfs.lodjinha.R
import br.com.igorfs.lodjinha.vo.ProductVo

class ProductsAdapter : RecyclerView.Adapter<ProductsViewHolder>() {

    private var products: ArrayList<ProductVo> = arrayListOf()

    fun loadItems(newList: List<ProductVo>) {
        products.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_list_item_layout, parent, false)
        return ProductsViewHolder(view)
    }

    override fun getItemCount() = products.size

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        val item = products[position]
        holder.bindView(item)
    }
}