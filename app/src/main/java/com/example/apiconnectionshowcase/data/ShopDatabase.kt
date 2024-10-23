package com.example.apiconnectionshowcase.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [ProductEntity::class, CategoryEntity::class], version = 1)
@TypeConverters(ImageTypeConverters::class)
abstract class ShopDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
    abstract fun categoryDao(): CategoryDao
}