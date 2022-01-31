package com.rasenyer.schoolapp.ui.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class SchoolViewModelFactory(private var application: Application) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(SchoolViewModel::class.java)) {
            return SchoolViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }

}