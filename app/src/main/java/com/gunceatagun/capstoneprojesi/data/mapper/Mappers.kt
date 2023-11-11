package com.gunceatagun.capstoneprojesi.data.mapper

import com.gunceatagun.capstoneprojesi.data.model.response.Product
import com.gunceatagun.capstoneprojesi.data.model.response.ProductListUI
import com.gunceatagun.capstoneprojesi.data.model.response.ProductUI

fun Product.mapToProductUI() =
    ProductUI(
        id = id ?: 1,
        title = title.orEmpty(),
        price = price ?: 0.0,
        salePrice = salePrice ?: 0.0,
        description = description.orEmpty(),
        category = category.orEmpty(),
        imageOne = imageOne.orEmpty(),
        imageTwo = imageTwo.orEmpty(),
        imageThree = imageThree.orEmpty(),
        rate = rate ?: 0.0,
        count = count ?: 0,
        saleState = saleState ?: false,
    )

fun List<Product>.mapToProductListUI() =
    map {
        ProductListUI(
            id = it.id ?: 1,
            title = it.title.orEmpty(),
            price = it.price ?: 0.0,
            salePrice = it.salePrice ?: 0.0,
            imageOne = it.imageOne.orEmpty(),
            saleState = it.saleState ?: false,
        )
    }