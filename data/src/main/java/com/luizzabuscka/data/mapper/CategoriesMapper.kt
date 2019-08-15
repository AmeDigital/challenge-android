package com.luizzabuscka.data.mapper

import com.luizzabuscka.commons.model.Category
import com.luizzabuscka.data.webservice.CategoriesResponse

class CategoriesMapper {

    fun transform(response: CategoriesResponse): List<Category> {
        val categories = arrayListOf<Category>()
        for (categoryWS in response.categories) {
            categories.add(Category(categoryWS.id, categoryWS.description, categoryWS.urlImage))
        }
        return categories
    }

}