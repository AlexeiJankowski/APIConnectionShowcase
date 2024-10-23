package com.example.apiconnectionshowcase.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Product(
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String,
    @SerialName("price")
    val price: Double,
    @SerialName("description")
    val description: String,
    @SerialName("category")
    val category: String,
    @SerialName("image")
    val image: String,
    @SerialName("rating")
    val rating: Rating,
    var quantity: Int = 1
)

@Serializable
data class Rating(
    @SerialName("rate")
    val rate: Double,
    @SerialName("count")
    val count: Int
)