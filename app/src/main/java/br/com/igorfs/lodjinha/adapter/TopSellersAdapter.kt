package br.com.igorfs.lodjinha.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.igorfs.lodjinha.R
import br.com.igorfs.lodjinha.vo.ProductVo

class TopSellersAdapter : RecyclerView.Adapter<TopSellersViewHolder>() {

    private var topSellersProducts: ArrayList<ProductVo> = arrayListOf()

    fun loadItems(newList: List<ProductVo>) {
        topSellersProducts.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopSellersViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.top_sellers_item_layout, parent, false)
        return TopSellersViewHolder(view)
    }

    override fun getItemCount() = topSellersProducts.size

    override fun onBindViewHolder(holder: TopSellersViewHolder, position: Int) {
        val item = topSellersProducts[position]
        holder.bindView(item)
    }
}