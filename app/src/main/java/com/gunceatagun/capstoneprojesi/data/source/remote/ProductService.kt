package com.gunceatagun.capstoneprojesi.data.source.remote

import com.gunceatagun.capstoneprojesi.data.model.request.AddCartRequestModel
import com.gunceatagun.capstoneprojesi.data.model.request.ClearCartRequestModel
import com.gunceatagun.capstoneprojesi.data.model.request.DeleteCartRequestModel
import com.gunceatagun.capstoneprojesi.data.model.response.BaseResponse
import com.gunceatagun.capstoneprojesi.data.model.response.GetCartProductResponse
import com.gunceatagun.capstoneprojesi.data.model.response.GetProductDetailResponse
import com.gunceatagun.capstoneprojesi.data.model.response.GetProductsByCategoryResponse
import com.gunceatagun.capstoneprojesi.data.model.response.GetProductsResponse
import com.gunceatagun.capstoneprojesi.data.model.response.GetSaleProductsResponse
import com.gunceatagun.capstoneprojesi.data.model.response.SearchProductResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ProductService {

    @GET("get_products.php")
    suspend fun getProducts(): Response<GetProductsResponse>

    @GET("get_sale_products.php")
    suspend fun getSaleProducts(): Response<GetSaleProductsResponse>

    @GET("get_products_by_category.php")
    suspend fun getProductsByCategory(
        @Query("category") category: String
    ): Response<GetProductsByCategoryResponse>

    @GET("get_product_detail.php")
    suspend fun getProductDetail(
        @Query("id") id: Int
    ): Response<GetProductDetailResponse>

    @GET("get_cart_products.php")
    suspend fun getCartProducts(
        @Query("userId") userId: String
    ): Response<GetCartProductResponse>

    @POST("add_to_cart.php")
    suspend fun addToCart(@Body addToCart: AddCartRequestModel): Response<BaseResponse>

    @POST("delete_from_cart.php")
    suspend fun deleteFromCart(@Body deleteFromCart: DeleteCartRequestModel): Response<BaseResponse>

    @POST("clear_cart.php")
    suspend fun clearCart(@Body clearCart: ClearCartRequestModel): Response<BaseResponse>

    @GET("search_product.php")
    suspend fun searchProduct(
        @Query("query") query: String
    ): Response<SearchProductResponse>
}