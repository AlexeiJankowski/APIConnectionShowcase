package com.example.apiconnectionshowcase.data

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class CategoryRepositoryImpl(
    private val shopAPI: ShopAPI,
    private val dispatcher: CoroutineDispatcher
): CategoryRepository {
    override suspend fun getCategories(): NetworkResult<List<String>> {
        return withContext(dispatcher) {
            try {
                val response = shopAPI.fetchCategories()

                if (response.isSuccessful) {
                    NetworkResult.Success(response.body()!!)
                } else {
                    NetworkResult.Error(response.errorBody().toString())
                }
            } catch(e: Exception) {
                NetworkResult.Error(e.message ?: "Unknown error")
            }
        }
    }
}