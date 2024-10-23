package com.example.apiconnectionshowcase.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ShopAPI {
    @GET("products/categories")
    suspend fun fetchCategories(): Response<List<String>>

    @GET("products")
    suspend fun getProducts(): Response<List<Product>>

    @GET("products")
    suspend fun getLimitedProducts(
        @Query("limit") limit: Int
    ): Response<List<Product>>

    @GET("products/category/{category}")
    suspend fun getProductsOfCategory(
        @Path("category") category: String
    ): Response<List<Product>>

    @GET("products/{id}")
    suspend fun getProduct(
        @Path("id") id: Int
    ): Response<Product>
}