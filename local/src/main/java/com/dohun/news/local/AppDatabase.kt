package com.dohun.news.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.dohun.news.local.dao.NewsDao
import com.dohun.news.local.entity.NewsEntity

@Database(
    entities = [
        NewsEntity::class
    ],
    version = 2,
    exportSchema = false
)
@TypeConverters(DatabaseConverters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun newsDao(): NewsDao
}

class DatabaseConverters {

    @TypeConverter
    fun fromStringListToString(from: List<String>?) = from?.joinToString(",")

    @TypeConverter
    fun fromStringToStringList(from: String?) = from?.split(",")?.toList()
}