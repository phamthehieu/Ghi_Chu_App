package com.example.incomeandexpenditureapp.data.repository

import androidx.annotation.WorkerThread
import com.example.incomeandexpenditureapp.data.local.IconsDao
import com.example.incomeandexpenditureapp.data.model.Icon
import kotlinx.coroutines.flow.Flow

class IconsRepository(private val iconsDao: IconsDao) {
    val allIcons: Flow<List<Icon>> = iconsDao.getAllIcons()

    @WorkerThread
    suspend fun insertAll(icons: List<Icon>) {
        iconsDao.insertAll(icons)
    }
}