package com.gunceatagun.capstoneprojesi.data.model.response

data class SearchProductResponse(
    val products: List<Product>?,
) : BaseResponse()
