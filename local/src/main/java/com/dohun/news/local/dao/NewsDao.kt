package com.dohun.news.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dohun.news.local.entity.NewsEntity

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertList(newsList: List<NewsEntity>)

    @Query("select * from News")
    suspend fun selectList(): List<NewsEntity>?

    @Query("delete from News")
    suspend fun deleteList()
}