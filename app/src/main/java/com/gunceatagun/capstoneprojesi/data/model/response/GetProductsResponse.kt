package com.gunceatagun.capstoneprojesi.data.model.response

data class GetProductsResponse(
    val products: List<Product>?,
    val status: Int?,
    val message: String?,
)
