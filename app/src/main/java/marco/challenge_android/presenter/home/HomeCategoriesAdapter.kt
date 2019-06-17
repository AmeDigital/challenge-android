package marco.challenge_android.presenter.home

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_category.view.*
import marco.challenge_android.R
import marco.challenge_android.Utils
import marco.challenge_android.data.Category

class HomeCategoriesAdapter(private val listener: (category: Category?) -> Unit) : RecyclerView.Adapter<HomeCategoriesAdapter.HomeCategoriesViewHolder>() {

    private var categoryList = arrayListOf<Category>()
    fun setCategories(categoryList: List<Category>) {
        this.categoryList.clear()
        this.categoryList.addAll(categoryList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeCategoriesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return HomeCategoriesViewHolder(view)
    }

    override fun getItemCount() = categoryList.size
    override fun onBindViewHolder(holder: HomeCategoriesViewHolder, position: Int) {
        holder.bindItem(categoryList[position])
    }

    inner class HomeCategoriesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindItem(category: Category) {
            with(itemView) {
                Utils.loadImage(this.context, category.urlImage, img_category)
                txt_category_name.text = category.description
                setOnClickListener {
                    listener.invoke(category)
                }
            }
        }

    }

}