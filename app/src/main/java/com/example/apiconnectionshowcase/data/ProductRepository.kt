package com.example.apiconnectionshowcase.data

interface ProductRepository {
    suspend fun getProducts(category: String): NetworkResult<List<Product>>
    suspend fun getLimitedProducts(limit: Int): NetworkResult<List<Product>>
    suspend fun getProduct(id: Int): NetworkResult<Product>
}