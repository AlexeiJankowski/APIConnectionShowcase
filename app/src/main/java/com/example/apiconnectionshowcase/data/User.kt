package com.example.apiconnectionshowcase.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    @SerialName("id")
    val id: Int,
    @SerialName("email")
    val email: String,
    @SerialName("username")
    val username: String,
    @SerialName("password")
    val password: String,
    @SerialName("phone")
    val phone: String,
    @SerialName("name")
    val name: Name,
    @SerialName("address")
    val address: Address
)

@Serializable
data class Name(
    @SerialName("firstname")
    val firstname: String,
    @SerialName("lastname")
    val lastname: String
)

@Serializable
data class Address(
    @SerialName("city")
    val city: String,
    @SerialName("street")
    val street: String,
    @SerialName("number")
    val number: Int,
    @SerialName("zipcode")
    val zipcode: String,
    @SerialName("geolocation")
    val geolocation: Geolocation
)

@Serializable
data class Geolocation(
    @SerialName("lat")
    val lat: String,
    @SerialName("long")
    val long: String
)