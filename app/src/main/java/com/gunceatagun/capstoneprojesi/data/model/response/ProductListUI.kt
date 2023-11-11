package com.gunceatagun.capstoneprojesi.data.model.response

data class ProductListUI(
    val id: Int,
    val title: String,
    val price: Double,
    val salePrice: Double,
    val imageOne: String,
    val saleState: Boolean,
)
