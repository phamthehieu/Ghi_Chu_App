package com.example.myapplication.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.local.IncomeAndExpenditureDatabase
import com.example.myapplication.data.model.Icons
import com.example.myapplication.data.repository.IconsRepository

class IconViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: IconsRepository
    val allIcons: LiveData<List<Icons>>

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