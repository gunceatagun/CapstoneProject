package com.gunceatagun.capstoneprojesi.data.model.response

data class GetProductsByCategoryResponse(
    val products: List<Product>?,
) : BaseResponse()

