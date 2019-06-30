package com.example.lodjinha.home

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.Product
import com.example.lodjinha.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.best_seller_item.view.*
import kotlinx.android.synthetic.main.best_seller_item.view.tvNewPrice
import kotlinx.android.synthetic.main.best_seller_item.view.tvOldPrice
import kotlinx.android.synthetic.main.fragment_product.view.*

class BestSellerAdapter(val onclickListener: (Int) -> Unit) : RecyclerView.Adapter<BestSellerAdapter.BestSellerVH>() {

    var listBestSellers : List<Product> = listOf()

    fun populateBestSellers(list: List<Product>){
        listBestSellers = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BestSellerVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.best_seller_item, parent, false)
        return BestSellerVH(view)
    }

    override fun getItemCount(): Int {
        return listBestSellers.size
    }

    override fun onBindViewHolder(holder: BestSellerVH, position: Int) {
        holder.bind(listBestSellers[position])
    }

   inner class BestSellerVH(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: Product) {
            itemView.apply {
                Picasso.get().load(item.imageUrl).into(ivBestSeller, object : Callback {
                    override fun onSuccess() {}
                    override fun onError(e: Exception) {
                        ivProduct?.setImageResource(R.drawable.default_image)
                    }
                })
                setOnClickListener {
                    onclickListener(item.id)
                }
                tvBestSellerDescription?.text = item.name
                tvOldPrice?.text = "De: ${item.oldPrice.toString()}"
                tvOldPrice?.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                tvNewPrice?.text = "Por ${item.newPrice.toString()}"
            }
        }
    }

}