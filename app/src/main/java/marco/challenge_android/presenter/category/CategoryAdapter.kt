package marco.challenge_android.presenter.category

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import marco.challenge_android.BuildConfig
import marco.challenge_android.R
import marco.challenge_android.data.Product
import marco.challenge_android.presenter.product.LoadingProductsViewHolder
import marco.challenge_android.presenter.product.NoProductsViewHolder
import marco.challenge_android.presenter.product.ProductViewHolder

class CategoryAdapter(private val listener: (product: Product?) -> Unit) : RecyclerView.Adapter<CategoryAdapter.CategoryProductsViewHolder<Any>>() {

    private val itens = arrayListOf<Any>()

    fun setItens(itens: List<Any>?, loading: Boolean = false) {
        remoteLastItem()
        itens?.let {
            this.itens.addAll(it)
            if (it.isNotEmpty() && loading) {
                if (this.itens.size == BuildConfig.LIMIT_OFFSET) {
                    this.itens.add(CategoryViewHolderType.LOADING.abreviation)
                }
            }
        }
        if (this.itens.isEmpty()) {
            this.itens.add(CategoryViewHolderType.NO_PRODUCT.abreviation)
        }
        notifyDataSetChanged()
    }

    private fun remoteLastItem() {
        if (itemCount > 0 && itemCount == this.itens.size) {
            val lastItem = itens.size -1
            if (this.itens[lastItem] == CategoryViewHolderType.LOADING.abreviation)
                this.itens.removeAt(lastItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryProductsViewHolder<Any> {
        val view: View
        if (viewType == CategoryViewHolderType.PRODUCT.ordinal) {
            view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
            return ProductViewHolder(view, listener)
        } else if (viewType == CategoryViewHolderType.NO_PRODUCT.ordinal) {
            view = LayoutInflater.from(parent.context).inflate(R.layout.item_no_products, parent, false)
            return NoProductsViewHolder(view)
        }
        return LoadingProductsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_loading, parent, false))
    }

    override fun getItemCount() = itens.size

    override fun onBindViewHolder(holder: CategoryProductsViewHolder<Any>, position: Int) {
        val any = itens[position]
        holder.onCategoryProductsBindViewHolder(any)
    }

    override fun getItemViewType(position: Int): Int {
        val any = itens[position]
        return when {
            any is Product -> CategoryViewHolderType.PRODUCT.ordinal
            any.toString() == CategoryViewHolderType.LOADING.abreviation -> CategoryViewHolderType.LOADING.ordinal
            any.toString() == CategoryViewHolderType.NO_PRODUCT.abreviation -> CategoryViewHolderType.NO_PRODUCT.ordinal
            else -> -1
        }
    }

    abstract class CategoryProductsViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var item: T? = null
        protected fun onBindViewHolder(item: T) {
            this.item = item
        }
        abstract fun onCategoryProductsBindViewHolder(item: T)
    }

}