package com.dohun.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dohun.local.dao.NewsDao
import com.dohun.local.entity.NewsEntity

@Database(
    entities = [
        NewsEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun newsDao(): NewsDao
}