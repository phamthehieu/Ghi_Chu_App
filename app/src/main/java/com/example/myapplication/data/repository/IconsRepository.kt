package com.example.myapplication.data.repository

import androidx.annotation.WorkerThread
import com.example.myapplication.data.local.IconsDao
import com.example.myapplication.data.model.Icons
import kotlinx.coroutines.flow.Flow

class IconsRepository(private val iconsDao: IconsDao) {
    val allIcons: Flow<List<Icons>> = iconsDao.getAllIcons()

    @WorkerThread
    suspend fun insertAll(icons: List<Icons>) {
        iconsDao.insertAll(icons)
    }
}