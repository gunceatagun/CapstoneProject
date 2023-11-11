package com.gunceatagun.capstoneprojesi.data.source.remote

import com.gunceatagun.capstoneprojesi.data.model.response.GetProductDetailResponse
import com.gunceatagun.capstoneprojesi.data.model.response.GetProductsResponse
import com.gunceatagun.capstoneprojesi.data.model.response.GetSaleProductsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductService {

    @GET("get_products.php")
    suspend fun getProducts(): Response<GetProductsResponse>

    @GET("get_sale_products.php")
    suspend fun getSaleProducts(): Response<GetSaleProductsResponse>

    @GET("get_product_detail.php")
    suspend fun getProductDetail(
        @Query("id") id: Int
    ): Response<GetProductDetailResponse>
}