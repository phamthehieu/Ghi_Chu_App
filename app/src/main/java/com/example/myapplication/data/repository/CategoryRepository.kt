package com.example.myapplication.data.repository

import androidx.annotation.WorkerThread
import com.example.myapplication.data.local.CategoryDao
import com.example.myapplication.data.model.Category
import com.example.myapplication.data.model.CategoryWithIcon
import kotlinx.coroutines.flow.Flow

class CategoryRepository(private val categoryDao: CategoryDao) {
    val allCategories: Flow<List<CategoryWithIcon>> = categoryDao.getAllCategory()

    @WorkerThread
    fun insertAll(category: List<Category>) {
        categoryDao.insertAllCategory(category)
    }

    @WorkerThread
    suspend fun deleteCategory(category: Category) {
        categoryDao.deleteCategory(category)
    }

    @WorkerThread
    suspend fun updateCategory(category: Category) {
        categoryDao.updateCategory(category)
    }

    @WorkerThread
    fun deleteAllCategory() {
        categoryDao.deleteAllCategoryType()
    }

}