package com.example.apiconnectionshowcase.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {
    @Query("SELECT * FROM Category")
    fun getCategories(): Flow<List<CategoryEntity>>
    @Insert
    suspend fun insert(categoryEntity: CategoryEntity)
}