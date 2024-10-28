package com.example.apiconnectionshowcase.data

interface UserRepository {
    suspend fun login(username: String, password: String): Int?
    suspend fun getCartByUserId(userId: Int): Cart?
}