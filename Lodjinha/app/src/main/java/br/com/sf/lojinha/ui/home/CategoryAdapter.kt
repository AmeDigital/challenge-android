package br.com.sf.lojinha.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.sf.lojinha.R
import br.com.sf.lojinha.repository.Category
import butterknife.BindView
import butterknife.ButterKnife
import com.squareup.picasso.Picasso
import java.util.*

class CategoryAdapter(private val listener: (Category) -> Unit) : RecyclerView.Adapter<ViewHolder>() {

    private val categories: MutableList<Category> = LinkedList()

    fun addCategories(categories: List<Category>) {
        this.categories.addAll(categories)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.list_item_category, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = categories.get(position)
        holder.bind(category, listener)
    }

}

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    @BindView(R.id.imCategory)
    lateinit var imCategory: ImageView

    @BindView(R.id.tvCategoryDescription)
    lateinit var tvDescription: TextView

    fun bind(category: Category, listener: (Category) -> Unit) {
        ButterKnife.bind(this, itemView)

        Picasso.get()
            .load(category.imageUrl.replace("http://", "https:"))
            .placeholder(R.drawable.ic_photo_grey_600_24dp)
            .fit()
            .noFade()
            .into(imCategory)

        tvDescription.text = category.description

        itemView.setOnClickListener { listener(category) }
    }
}