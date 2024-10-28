package com.example.apiconnectionshowcase.data

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Query("SELECT * FROM Product")
    suspend fun getProducts(): List<ProductEntity>
}