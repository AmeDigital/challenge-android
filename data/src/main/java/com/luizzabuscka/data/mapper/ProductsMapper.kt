package com.luizzabuscka.data.mapper

import com.luizzabuscka.commons.model.Category
import com.luizzabuscka.commons.model.Product
import com.luizzabuscka.data.webservice.ProductsResponse

class ProductsMapper {

    fun transform(response: ProductsResponse): List<Product> {
        val products = arrayListOf<Product>()
        for (productWS in response.products) {
            products.add(
                Product(
                    productWS.id,
                    productWS.name,
                    productWS.urlImage,
                    productWS.description,
                    String.format("%.2f", productWS.priceFrom.toFloat()),
                    String.format("%.2f", productWS.price.toFloat()),
                    Category(
                        productWS.category.id,
                        productWS.category.description,
                        productWS.category.urlImage
                    )
                )
            )
        }
        return products
    }

}