package marco.challenge_android.presenter.product

import android.view.View
import marco.challenge_android.presenter.category.CategoryAdapter

class LoadingProductsViewHolder(val view: View) : CategoryAdapter.CategoryProductsViewHolder<Any>(view) {

    override fun onCategoryProductsBindViewHolder(item: Any) {
        super.onBindViewHolder(item)
    }

}