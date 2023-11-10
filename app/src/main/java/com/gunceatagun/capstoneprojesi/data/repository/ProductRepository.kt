package com.gunceatagun.capstoneprojesi.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.gunceatagun.capstoneprojesi.data.model.GetProductDetailResponse
import com.gunceatagun.capstoneprojesi.data.model.GetProductsResponse
import com.gunceatagun.capstoneprojesi.data.model.Product
import com.gunceatagun.capstoneprojesi.data.source.remote.ProductService
import kotlinx.coroutines.flow.combine
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductRepository(private val productService: ProductService) {
    var productsLiveData = MutableLiveData<List<Product>>()

    var productDetailLiveData = MutableLiveData<Product>()

    var errorDataLiveData = MutableLiveData<String>()
    var loadingLiveData = MutableLiveData<Boolean>()


    fun getProducts() {
        loadingLiveData.value = true
        productService.getProducts().enqueue(object :
            Callback<GetProductsResponse> {
            override fun onResponse(
                call: Call<GetProductsResponse>,
                response: Response<GetProductsResponse>
            ) {
                val results = response.body()

                if (response.isSuccessful) {
                    productsLiveData.value = results?.products.orEmpty()
                } else {
                    errorDataLiveData.value = results?.message.orEmpty()
                }
                loadingLiveData.value = false
            }

            override fun onFailure(call: Call<GetProductsResponse>, t: Throwable) {
                Log.e("GetProducts", t.message.orEmpty())
                errorDataLiveData.value = t.message.orEmpty()
                loadingLiveData.value = false
            }

        })
    }

    fun getProductDetail(id:Int){
        loadingLiveData.value = true
        productService.getProductDetail(id).enqueue(object :
            Callback<GetProductDetailResponse>{
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

}
