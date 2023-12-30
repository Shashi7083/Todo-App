package com.example.todoapp.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedDataViewModel :ViewModel(){

    private var _selectedPriority by mutableStateOf(3)
    val selectedPriority :Int  get() =  _selectedPriority

    fun setSelectedPriority(priority:Int){
        _selectedPriority = priority
    }
}