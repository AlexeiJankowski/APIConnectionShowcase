package com.example.apiconnectionshowcase.data

interface CategoryRepository {
    suspend fun getCategories(): NetworkResult<List<String>>
}