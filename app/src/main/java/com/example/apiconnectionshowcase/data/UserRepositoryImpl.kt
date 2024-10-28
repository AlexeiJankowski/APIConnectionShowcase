package com.example.apiconnectionshowcase.data

import android.util.Log

class UserRepositoryImpl(
    private val api: ShopAPI
): UserRepository {
    override suspend fun login(username: String, password: String): Int? {
        val users = api.getUsers()
        users.forEach {
            Log.d("username and password", it.username + " " + it.password)
        }
        Log.d("actual username and password", "$username $password")
        return users.find { it.username == username && it.password == password }?.id
    }

    override suspend fun getCartByUserId(userId: Int): Cart? {
        val response = api.getAllCarts()
        if (response.isSuccessful) {
            return response.body()?.find { it.userId == userId }
        }
        return null
    }
}