package com.example.apiconnectionshowcase.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Category(
    @SerialName("id")
    val id: Long,
    @SerialName("name")
    val name: String,
    @SerialName("image")
    val image: String
)
