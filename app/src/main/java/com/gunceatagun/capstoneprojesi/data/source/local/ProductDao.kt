package com.gunceatagun.capstoneprojesi.data.source.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gunceatagun.capstoneprojesi.data.model.response.ProductEntity

@Dao
interface ProductDao {
    @Query("SELECT * FROM fav_products")
    suspend fun getProducts():List<ProductEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addProduct(productEntity: ProductEntity)

    @Delete
    suspend fun deleteProduct(productEntity: ProductEntity)

}