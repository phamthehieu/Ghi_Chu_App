package com.example.incomeandexpenditureapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.incomeandexpenditureapp.data.local.IncomeAndExpenditureDatabase
import com.example.incomeandexpenditureapp.data.model.Icon
import com.example.incomeandexpenditureapp.data.repository.IconsRepository

class IconViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: IconsRepository
    val allIcons: LiveData<List<Icon>>

    init {
        val iconDao = IncomeAndExpenditureDatabase.getDatabase(application, viewModelScope).iconDao()
        repository = IconsRepository(iconDao)
        allIcons = repository.allIcons.asLiveData()
    }
}

class IconViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(IconViewModel::class.java)) {
            return IconViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
