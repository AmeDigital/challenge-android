package br.com.sf.lojinha.ui.products_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.sf.lojinha.R
import br.com.sf.lojinha.repository.Product
import butterknife.BindView
import butterknife.ButterKnife
import com.squareup.picasso.Picasso
import java.util.*

class ProductsAdapter(private val listener: (Product) -> Unit) : RecyclerView.Adapter<ViewHolder>() {

    private val products: MutableList<Product> = LinkedList()

    fun addProducts(products: List<Product>) {
        this.products.addAll(products)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_product, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products.get(position)
        holder.bind(product, listener)
    }

}

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    @BindView(R.id.imProduct)
    lateinit var imProduct: ImageView

    @BindView(R.id.tvDescription)
    lateinit var tvDescription: TextView

    @BindView(R.id.tvPriceFrom)
    lateinit var tvPriceFrom: TextView

    @BindView(R.id.tvPriceBy)
    lateinit var tvPriceBy: TextView

    fun bind(product: Product, listener: (Product) -> Unit) {
        ButterKnife.bind(this, itemView)

        Picasso.get()
            .load(product.image)
            .placeholder(R.drawable.ic_photo_grey_600_24dp)
            .fit()
            .noFade()
            .into(imProduct)

        tvDescription.text = product.description
        tvPriceFrom.text = String.format("De %s", product.priceFrom)
        tvPriceBy.text = String.format("Por %s", product.priceBy)

        itemView.setOnClickListener { listener(product) }
    }
}