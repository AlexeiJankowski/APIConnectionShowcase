package com.example.apiconnectionshowcase.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName

@Entity(tableName = "Product")
data class ProductEntity(
    @PrimaryKey
    val id: Long,
    val title: String,
    val price: Double,
    val description: String,
    val image: String,
    val category: String
)
