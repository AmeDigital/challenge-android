package com.lodjinha.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.lodjinha.R
import com.lodjinha.model.Categories
import com.lodjinha.model.Product

//class HomeListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

//    private lateinit var items: List<Product>
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val binding: ItemPostBinding =
//            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_post, parent, false)
//        return ViewHolder(binding)
//    }
//
//    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//
//    }
//
//    override fun getItemCount(): Int {
//        return if (::postList.isInitialized) postList.size else 0
//    }
//
//    fun updatePostList(postList: List<Post>) {
//        this.postList = postList
//        notifyDataSetChanged()
//    }
//
//    class ViewHolder(private val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root) {
//        private val viewModel = PostViewModel()
//
//        fun bind(post: Post) {
//            viewModel.bind(post)
//            binding.viewModel = viewModel
//        }
//    }
//}