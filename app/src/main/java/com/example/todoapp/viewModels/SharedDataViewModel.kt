package com.example.todoapp.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedDataViewModel :ViewModel(){

    private val _selectedPriority = MutableLiveData(3)
    val selectedPriority : LiveData<Int> get() = _selectedPriority

    fun setSelectedPriority(priority:Int){
        _selectedPriority.value = priority
    }
}