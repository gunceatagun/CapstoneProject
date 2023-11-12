package com.gunceatagun.capstoneprojesi.data.model.response

data class GetCartProductResponse(
    val products: List<Product>?,
) : BaseResponse()