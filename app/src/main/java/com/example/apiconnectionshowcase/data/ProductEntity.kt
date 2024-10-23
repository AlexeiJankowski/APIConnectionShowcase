package com.example.apiconnectionshowcase.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "Product",
    foreignKeys = [ForeignKey(
        entity = CategoryEntity::class,
        parentColumns = ["id"],
        childColumns = ["categoryId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class ProductEntity(
    @PrimaryKey
    val id: Long,
    val title: String,
    val price: Double,
    val description: String,
    val categoryId: Long,
    val images: List<String>
)
