package com.task.movieapp.data.db.converters

import androidx.room.TypeConverter

class StringListConverter {

    @TypeConverter
    fun fromString(stringList: String?): List<String>? {
        return stringList?.split(",")?.map { it }
    }

    @TypeConverter
    fun toString(list: List<String>?): String? {
        return list?.joinToString(separator = ",")
    }
}