package br.com.igorfs.lodjinha.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import br.com.igorfs.lodjinha.vo.HomeBannerVo
import android.support.v7.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import android.view.ViewGroup
import br.com.igorfs.lodjinha.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.header_item_layout.view.*

class HomeHeaderListAdapter :
    Adapter<HomeHeaderListAdapter.HeaderListViewHolder>() {

    private var headerList: ArrayList<HomeBannerVo> = arrayListOf()

    fun loadItems(newList: List<HomeBannerVo>){
        headerList.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeaderListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.header_item_layout, parent, false)
        return HeaderListViewHolder(view)
    }

    override fun getItemCount() = headerList.size

    override fun onBindViewHolder(holder: HeaderListViewHolder, position: Int) {
        val item = headerList[position]
        holder.bindView(item)
    }


    inner class HeaderListViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private val imageView = itemView.header_item_imageview

        fun bindView(item: HomeBannerVo){
            Glide
                .with(itemView)
                .load(item.urlImagem)
                .centerCrop()
                .into(imageView)
        }
    }
}