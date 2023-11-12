package com.gunceatagun.capstoneprojesi.data.mapper

import com.gunceatagun.capstoneprojesi.data.model.response.Product
import com.gunceatagun.capstoneprojesi.data.model.response.ProductEntity
import com.gunceatagun.capstoneprojesi.data.model.response.ProductUI

fun Product.mapToProductUI(favorites: List<Int>) =
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
        isFav = favorites.contains(id)
    )

fun List<Product>.mapProductToProductUI(favorites: List<Int>) =
    map {
        ProductUI(
            id = it.id ?: 1,
            title = it.title.orEmpty(),
            price = it.price ?: 0.0,
            salePrice = it.salePrice ?: 0.0,
            description = it.description.orEmpty(),
            category = it.category.orEmpty(),
            imageOne = it.imageOne.orEmpty(),
            imageTwo = it.imageTwo.orEmpty(),
            imageThree = it.imageThree.orEmpty(),
            rate = it.rate ?: 0.0,
            count = it.count ?: 1,
            saleState = it.saleState ?: false,
            isFav = favorites.contains(it.id)
        )
    }

fun ProductUI.mapToProductEntity() =
    ProductEntity(
        productId = id,
        title = title,
        price = price,
        salePrice = salePrice,
        description = description,
        category = category,
        imageOne = imageOne,
        imageTwo = imageTwo,
        imageThree = imageThree,
        rate = rate,
        count = count,
        saleState = saleState
    )

fun List<ProductEntity>.mapProductEntityToProductUI() =
    map { entity ->
        ProductUI(
            id = entity.productId ?: 1,
            title = entity.title.orEmpty(),
            price = entity.price ?: 0.0,
            salePrice = entity.salePrice ?: 0.0,
            description = entity.description.orEmpty(),
            category = entity.category.orEmpty(),
            imageOne = entity.imageOne.orEmpty(),
            imageTwo = entity.imageTwo.orEmpty(),
            imageThree = entity.imageThree.orEmpty(),
            rate = entity.rate ?: 0.0,
            count = entity.count ?: 1,
            saleState = entity.saleState ?: false,
        )
    }
