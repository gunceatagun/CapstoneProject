package com.gunceatagun.capstoneprojesi.data.repository

import com.gunceatagun.capstoneprojesi.common.Resource
import com.gunceatagun.capstoneprojesi.data.mapper.mapProductEntityToProductUI
import com.gunceatagun.capstoneprojesi.data.mapper.mapProductToProductUI
import com.gunceatagun.capstoneprojesi.data.mapper.mapToProductEntity
import com.gunceatagun.capstoneprojesi.data.mapper.mapToProductUI
import com.gunceatagun.capstoneprojesi.data.model.response.ProductUI
import com.gunceatagun.capstoneprojesi.data.source.local.ProductDao
import com.gunceatagun.capstoneprojesi.data.source.remote.ProductService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProductRepository(
    private val productService: ProductService,
    private val productDao: ProductDao
) {

    suspend fun getProducts(): Resource<List<ProductUI>> =
        withContext(Dispatchers.IO) {
            try {
                val favorites = productDao.getProductIds()
                val response = productService.getProducts().body()
                if (response?.status == 200) {
                    Resource.Success(response.products.orEmpty().mapProductToProductUI(favorites))
                } else {
                    Resource.Fail(response?.message.orEmpty())
                }
            } catch (e: Exception) {
                Resource.Error(e.message.orEmpty())
            }
        }

    suspend fun getSaleProducts(): Resource<List<ProductUI>> =
        withContext(Dispatchers.IO) {
            try {
                val favorites = productDao.getProductIds()
                val response = productService.getSaleProducts().body()
                if (response?.status == 200) {
                    Resource.Success(response.products.orEmpty().mapProductToProductUI(favorites))
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
                val favorites = productDao.getProductIds()
                val response = productService.getProductDetail(id).body()
                if (response?.status == 200 && response.product != null) {
                    Resource.Success(response.product.mapToProductUI(favorites))
                } else {
                    Resource.Fail(response?.message.orEmpty())
                }

            } catch (e: Exception) {
                Resource.Error(e.message.orEmpty())
            }
        }

    suspend fun addToFavorites(product: ProductUI) {
        productDao.addProduct(product.mapToProductEntity())
    }

    suspend fun deleteFromFavorites(product: ProductUI) {
        productDao.deleteProduct(product.mapToProductEntity())
    }

    suspend fun getFavorites(): Resource<List<ProductUI>> =
        withContext(Dispatchers.IO) {
            try {
                val products = productDao.getProducts()
                if (products.isEmpty()) {
                    Resource.Fail("Favorileriniz Boş")
                } else {
                    Resource.Success(products.mapProductEntityToProductUI())
                }

            } catch (e: Exception) {
                Resource.Error(e.message.orEmpty())
            }
        }


}
