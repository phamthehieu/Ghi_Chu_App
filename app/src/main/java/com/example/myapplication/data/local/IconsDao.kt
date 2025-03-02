package com.example.myapplication.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplication.data.model.Icons
import kotlinx.coroutines.flow.Flow

@Dao
interface IconsDao {

    @Query("SELECT * FROM icon_table")
    fun getAllIcons(): Flow<List<Icons>>

    @Insert
    suspend fun insertAll(icons: List<Icons>)

}