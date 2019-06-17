package marco.challenge_android.presenter.product

import android.view.View
import kotlinx.android.synthetic.main.item_product.view.*
import marco.challenge_android.R
import marco.challenge_android.Utils
import marco.challenge_android.data.Product
import marco.challenge_android.presenter.category.CategoryAdapter

class ProductViewHolder(val view: View, private val listener: (product: Product?) -> Unit) : CategoryAdapter.CategoryProductsViewHolder<Any>(view) {

    override fun onCategoryProductsBindViewHolder(item: Any) {
        super.onBindViewHolder(item)
        if (item is Product) {
            with(itemView) {
                Utils.loadImage(context, item.urlImage, img_product)

                txt_product_name.text = item.name
                txt_price_from.text = String.format(
                    context.getString(R.string.price_from),
                    Utils.formatCurrencyNew(item.priceFrom)
                )
                txt_price_to.text = String.format(
                    context.getString(R.string.price_to),
                    Utils.formatCurrencyNew(item.priceTo)
                )
                setOnClickListener {
                    listener.invoke(item)
                }
            }
        }
    }

}