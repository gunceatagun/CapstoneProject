package com.gunceatagun.capstoneprojesi

import android.app.Application
import com.gunceatagun.capstoneprojesi.data.source.remote.ProductService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainApplication : Application() {
    companion object {

        const val BASE_URL = "https://api.canerture.com/ecommerce/"

        var productService: ProductService? = null
        fun provideRetrofit() {

            val okHttpClient = OkHttpClient.Builder().apply {
                addInterceptor(
                    Interceptor { chain ->
                        val builder = chain.request().newBuilder()
                        builder.header("store","gncatagn")
                        return@Interceptor chain.proceed(builder.build())
                    }
                )
            }.build()

            val retrofit = Retrofit.Builder().apply {
                addConverterFactory(GsonConverterFactory.create())
                baseUrl(BASE_URL)
                client(okHttpClient)
            }.build()
            productService = retrofit.create(ProductService::class.java)
        }
    }

}