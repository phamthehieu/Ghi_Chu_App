package com.example.incomeandexpenditureapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.incomeandexpenditureapp.data.model.Icon
import kotlinx.coroutines.flow.Flow

@Dao
interface IconsDao {

    @Query("SELECT * FROM icon_table")
    fun getAllIcons(): Flow<List<Icon>>

    @Insert
   suspend fun insertAll(icons: List<Icon>)

}