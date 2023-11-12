package com.gunceatagun.capstoneprojesi.di

import com.gunceatagun.capstoneprojesi.data.repository.ProductRepository
import com.gunceatagun.capstoneprojesi.data.source.local.ProductDao
import com.gunceatagun.capstoneprojesi.data.source.remote.ProductService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideProductRepository(productService: ProductService, productDao: ProductDao) =
        ProductRepository(productService, productDao)
}