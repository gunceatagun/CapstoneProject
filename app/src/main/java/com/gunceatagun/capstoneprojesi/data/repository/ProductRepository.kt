package com.gunceatagun.capstoneprojesi.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.gunceatagun.capstoneprojesi.data.model.GetProductsResponse
import com.gunceatagun.capstoneprojesi.data.model.Product
import com.gunceatagun.capstoneprojesi.data.source.remote.ProductService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductRepository(private val productService: ProductService) {
    var productsLiveData = MutableLiveData<List<Product>>()

    fun getProducts() {
        productService.getProducts().enqueue(object :
            Callback<GetProductsResponse> {
            override fun onResponse(
                call: Call<GetProductsResponse>,
                response: Response<GetProductsResponse>
            ) {
                val results = response.body()
                if (results?.status == 200) {
                    productsLiveData.value = results.products.orEmpty()
                } else {
                    //400
                    // Toast.makeText(context,results?.message, Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<GetProductsResponse>, t: Throwable) {
                Log.e("GetProducts", t.message.orEmpty())
            }

        })
    }

}
