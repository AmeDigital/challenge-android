package br.com.igorfs.lodjinha.adapter

import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import br.com.igorfs.lodjinha.R
import br.com.igorfs.lodjinha.util.getLoaderPlaceholder
import br.com.igorfs.lodjinha.vo.HomeBannerVo
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.header_item_layout.view.header_item_imageview

class BannerListViewHolder (itemView: View) :
    RecyclerView.ViewHolder(itemView) {
    private val imageView = itemView.header_item_imageview

    fun bindView(item: HomeBannerVo) {
        Glide
            .with(itemView)
            .load(item.urlImagem)
            .placeholder(getLoaderPlaceholder(itemView.context))
            .error(R.drawable.ic_placeholder)
            .into(imageView)

        itemView.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(item.linkUrl)
            ContextCompat.startActivity(itemView.context, intent, null)
        }
    }
}