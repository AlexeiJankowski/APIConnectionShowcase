package com.example.apiconnectionshowcase.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Category")
data class CategoryEntity(
    @PrimaryKey
    val id: Long,
    val name: String,
    val image: String
)
