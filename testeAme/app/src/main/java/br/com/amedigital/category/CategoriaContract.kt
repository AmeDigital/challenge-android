package br.com.amedigital.banner

import br.com.amedigital.model.Category

interface CategoriaContract {
    interface View {
        fun setProgressIndicatorCategory(active: Boolean)

        fun showCategories(categories: ArrayList<Category>)

        fun showErrorCategory(message : String)
    }

    interface CategoryActionListener {
        fun loadCategories()
    }
}