package com.gunceatagun.capstoneprojesi.data.repository

import com.gunceatagun.capstoneprojesi.common.Resource
import com.gunceatagun.capstoneprojesi.data.model.Product
import com.gunceatagun.capstoneprojesi.data.source.remote.ProductService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProductRepository(private val productService: ProductService) {

    suspend fun getProducts(): Resource<List<Product>> =
        withContext(Dispatchers.IO) {
            try {
                val response = productService.getProducts().body()
                if (response?.status == 200) {
                    Resource.Success(response.products.orEmpty())
                } else {
                    Resource.Fail(response?.message.orEmpty())
                }
            } catch (e: Exception) {
                Resource.Error(e.message.orEmpty())
            }
        }

    suspend fun getProductDetail(id: Int): Resource<Product> =
        withContext(Dispatchers.IO) {
            try {
                val response = productService.getProductDetail(id).body()
                if (response?.status == 200 && response.product != null) {
                    Resource.Success(response.product)
                } else {
                    Resource.Fail(response?.message.orEmpty())
                }

            } catch (e: Exception) {
                Resource.Error(e.message.orEmpty())
            }

        }
}
