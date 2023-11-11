package com.gunceatagun.capstoneprojesi.data.repository

import com.gunceatagun.capstoneprojesi.common.Resource
import com.gunceatagun.capstoneprojesi.data.mapper.mapToProductListUI
import com.gunceatagun.capstoneprojesi.data.mapper.mapToProductUI
import com.gunceatagun.capstoneprojesi.data.model.response.ProductListUI
import com.gunceatagun.capstoneprojesi.data.model.response.ProductUI
import com.gunceatagun.capstoneprojesi.data.source.remote.ProductService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProductRepository(private val productService: ProductService) {

    suspend fun getProducts(): Resource<List<ProductListUI>> =
        withContext(Dispatchers.IO) {
            try {
                val response = productService.getProducts().body()
                if (response?.status == 200) {
                    Resource.Success(response.products.orEmpty().mapToProductListUI())
                } else {
                    Resource.Fail(response?.message.orEmpty())
                }
            } catch (e: Exception) {
                Resource.Error(e.message.orEmpty())
            }
        }

    suspend fun getSaleProducts(): Resource<List<ProductListUI>> =
        withContext(Dispatchers.IO) {
            try {
                val response = productService.getSaleProducts().body()
                if (response?.status == 200) {
                    Resource.Success(response.products.orEmpty().mapToProductListUI())
                } else {
                    Resource.Fail(response?.message.orEmpty())
                }
            } catch (e: Exception) {
                Resource.Error(e.message.orEmpty())
            }
        }

    suspend fun getProductDetail(id: Int): Resource<ProductUI> =
        withContext(Dispatchers.IO) {
            try {
                val response = productService.getProductDetail(id).body()
                if (response?.status == 200 && response.product != null) {
                    Resource.Success(response.product.mapToProductUI())
                } else {
                    Resource.Fail(response?.message.orEmpty())
                }

            } catch (e: Exception) {
                Resource.Error(e.message.orEmpty())
            }

        }
}
