package com.gunceatagun.capstoneprojesi.data.repository

import androidx.lifecycle.MutableLiveData
import com.gunceatagun.capstoneprojesi.common.Resource
import com.gunceatagun.capstoneprojesi.data.model.Product
import com.gunceatagun.capstoneprojesi.data.source.remote.ProductService

class ProductRepository(private val productService: ProductService) {
    //  var products = MutableLiveData<Resource<List<Product>>>()


    suspend fun getProducts(): Resource<List<Product>> {
        return try {
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

//    suspend fun getProductDetail(id: Int) {
//        products.value = Resource.Loading
//        try {
//
//        } catch (e: Exception) {
//
//        }
//
//    }

    /*
        fun getProductDetail(id: Int) {
            loadingLiveData.value = true
            productService.getProductDetail(id).enqueue(object :
                Callback<GetProductDetailResponse> {
                override fun onResponse(
                    call: Call<GetProductDetailResponse>,
                    response: Response<GetProductDetailResponse>
                ) {
                    val results = response.body()
                    if (response.isSuccessful && results?.product != null) {
                        productDetailLiveData.value = results.product
                    } else {
                        errorDataLiveData.value = results?.message.orEmpty()
                    }
                    loadingLiveData.value = false
                }

                override fun onFailure(call: Call<GetProductDetailResponse>, t: Throwable) {
                    Log.e("GetProducts", t.message.orEmpty())
                    errorDataLiveData.value = t.message.orEmpty()
                    loadingLiveData.value = false
                }

            })
        }
    */

}
