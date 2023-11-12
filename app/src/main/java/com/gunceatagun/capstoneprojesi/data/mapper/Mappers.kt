package com.gunceatagun.capstoneprojesi.data.mapper

import com.gunceatagun.capstoneprojesi.data.model.response.Product
import com.gunceatagun.capstoneprojesi.data.model.response.ProductEntity
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

fun List<Product>.mapProductToProductListUI() =
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

fun ProductListUI.mapToProductEntity() =
    ProductEntity(
        productId = id,
        title = title,
        price = price,
        salePrice = salePrice,
        imageOne = imageOne,
        saleState = saleState
    )

fun List<ProductEntity>.mapProductEntityToProductListUI() =
    map {entity->
        ProductListUI(
            id = entity.productId ?: 1,
            title = entity.title.orEmpty(),
            price = entity.price ?: 0.0,
            salePrice = entity.salePrice ?: 0.0,
            imageOne = entity.imageOne.orEmpty(),
            saleState = entity.saleState ?: false
        )
    }
