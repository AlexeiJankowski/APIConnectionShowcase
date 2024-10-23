package com.example.apiconnectionshowcase.data

import androidx.room.TypeConverter
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.Json

class ImageTypeConverters {
    @TypeConverter
    fun fromStringList(value: String): List<String> {
        return Json.decodeFromString(ListSerializer(String.serializer()), value)
    }

    @TypeConverter
    fun fromListString(list: List<String>): String {
        return Json.encodeToString(ListSerializer(String.serializer()), list)
    }
}