package com.leonardoalves.ametest.store.viewholder

import android.view.View
import com.leonardoalves.ametest.R
import com.leonardoalves.ametest.custom.ViewHolder
import com.leonardoalves.ametest.store.viewmodel.HeaderViewModel
import kotlinx.android.synthetic.main.view_store_header.view.*

const val STORE_HEADER_VIEW_ID = R.layout.view_store_header

class StoreHeaderViewHolder(itemView: View): ViewHolder<HeaderViewModel>(itemView) {
    override fun bind(viewModel: HeaderViewModel) {
        itemView.tvTitle.text = itemView.context.getString(viewModel.title)
    }

    override fun recycle() {}
}