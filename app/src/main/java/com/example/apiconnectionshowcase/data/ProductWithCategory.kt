package com.example.apiconnectionshowcase.data

import androidx.room.Embedded
import androidx.room.Relation

class ProductWithCategory (
    @Embedded val product: ProductEntity,
    @Relation(
        parentColumn = "categoryId",
        entityColumn = "id"
    )
    val category: CategoryEntity
)
