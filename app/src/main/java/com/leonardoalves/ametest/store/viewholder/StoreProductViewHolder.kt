package com.leonardoalves.ametest.store.viewholder

import android.annotation.SuppressLint
import android.view.View
import com.bumptech.glide.Glide
import com.leonardoalves.ametest.R
import com.leonardoalves.ametest.custom.ViewHolder
import com.leonardoalves.ametest.store.viewmodel.StoreProductViewModel
import kotlinx.android.synthetic.main.view_store_product.view.*

const val STORE_PRODUCT_VIEW_ID = R.layout.view_store_product

class StoreProductViewHolder(itemView: View) : ViewHolder<StoreProductViewModel>(itemView) {
    @SuppressLint("SetTextI18n")
    override fun bind(viewModel: StoreProductViewModel) {
        with(itemView){
            Glide.with(context)
                .load(viewModel.picture)
                .into(ivProduct)
            tvProductName.text = viewModel.name
            tvProductPrice.text = "Por ${viewModel.price}"
            tvProductPriceFrom.text = "De ${viewModel.originalPrice}"
        }
    }

    override fun recycle() {
        with(itemView){
            Glide.with(context)
                .clear(ivProduct)
        }
    }
}