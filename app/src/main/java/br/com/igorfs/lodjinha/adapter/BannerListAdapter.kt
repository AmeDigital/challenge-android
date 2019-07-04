package br.com.igorfs.lodjinha.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import br.com.igorfs.lodjinha.R
import br.com.igorfs.lodjinha.vo.HomeBannerVo

class BannerListAdapter : Adapter<BannerListViewHolder>() {

    private var headerList: ArrayList<HomeBannerVo> = arrayListOf()

    fun loadItems(newList: List<HomeBannerVo>) {
        headerList.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.header_item_layout, parent, false)
        return BannerListViewHolder(view)
    }

    override fun getItemCount() = headerList.size

    override fun onBindViewHolder(holder: BannerListViewHolder, position: Int) {
        val item = headerList[position]
        holder.bindView(item)
    }
}