package com.gunceatagun.capstoneprojesi.data.model.response

data class GetProductDetailResponse(
    val product: Product?,
    val status: Int?,
    val message: String?,
)
