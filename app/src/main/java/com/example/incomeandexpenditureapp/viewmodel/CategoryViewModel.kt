package com.example.incomeandexpenditureapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.incomeandexpenditureapp.data.local.IncomeAndExpenditureDatabase
import com.example.incomeandexpenditureapp.data.model.Category
import com.example.incomeandexpenditureapp.data.model.CategoryWithIcon
import com.example.incomeandexpenditureapp.data.repository.CategoryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoryViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: CategoryRepository
    val allCategory: LiveData<List<CategoryWithIcon>>

    init {
        val categoryDao = IncomeAndExpenditureDatabase.getDatabase(application, viewModelScope).categoryDao()
        repository = CategoryRepository(categoryDao)
        allCategory = repository.allCategories.asLiveData()
    }

    fun insert(category: Category) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertAll(listOf(category))
        }
    }

    fun delete(category: Category) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteCategory(category)
        }
    }

    fun update(category: Category) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateCategory(category)
        }
    }

    fun deleteAllCategoryWithType() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllCategory()
        }
    }

}

class CategoryViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CategoryViewModel::class.java)) {
            return CategoryViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}