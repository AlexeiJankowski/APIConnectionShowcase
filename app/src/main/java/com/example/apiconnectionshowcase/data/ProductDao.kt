package com.example.apiconnectionshowcase.data

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Transaction
    @Query("""
        SELECT * FROM Product
        INNER JOIN Category ON Product.categoryId = Category.id
        WHERE Product.id = :productId
    """)
    suspend fun getProducts(productId: Long): List<ProductWithCategory>
}