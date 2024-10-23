package com.example.apiconnectionshowcase.data

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class ProductRepositoryImpl(
    private val shopAPI: ShopAPI,
    private val dispatcher: CoroutineDispatcher
    ): ProductRepository {
    override suspend fun getProducts(category: String): NetworkResult<List<Product>> {
        return withContext(dispatcher) {
            try {
                val response = if (category == "") {
                    shopAPI.getProducts()
                } else {
                    shopAPI.getProductsOfCategory(category)
                }

                if (response.isSuccessful) {
                    NetworkResult.Success(response.body()!!)
                } else {
                    NetworkResult.Error(response.errorBody().toString())
                }
            } catch (e: Exception) {
                NetworkResult.Error(e.message ?: "Unknown error")
            }
        }
    }

    override suspend fun getLimitedProducts(limit: Int): NetworkResult<List<Product>> {
        return withContext(dispatcher) {
            try {
                val response = shopAPI.getLimitedProducts(limit)

                if (response.isSuccessful) {
                    NetworkResult.Success(response.body()!!)
                } else {
                    NetworkResult.Error(response.errorBody().toString())
                }
            } catch (e: Exception) {
                NetworkResult.Error(e.message ?: "Unknown error")
            }
        }
    }

    override suspend fun getProduct(id: Int): NetworkResult<Product> {
        return withContext(dispatcher) {
            try {
                val response = shopAPI.getProduct(id)

                if (response.isSuccessful) {
                    NetworkResult.Success(response.body()!!)
                } else {
                    NetworkResult.Error(response.errorBody().toString())
                }
            } catch (e: Exception) {
                NetworkResult.Error(e.message ?: "Unknown error")
            }
        }
    }
}