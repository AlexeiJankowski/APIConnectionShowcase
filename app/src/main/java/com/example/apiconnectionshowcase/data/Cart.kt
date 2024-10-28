package com.example.apiconnectionshowcase.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Cart(
    @SerialName("id")
    val id: Int,
    @SerialName("userId")
    val userId: Int,
    @SerialName("date")
    val date: String,
    @SerialName("products")
    val products: List<CartProduct>
)

@Serializable
data class CartProduct(
    @SerialName("productId")
    val productId: Int,
    @SerialName("quantity")
    val quantity: Int
)
