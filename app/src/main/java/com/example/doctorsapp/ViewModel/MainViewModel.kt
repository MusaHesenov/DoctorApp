package com.example.doctorsapp.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.doctorsapp.Model.DoctorModel
import com.example.doctorsapp.Repository.MainRepository

class MainViewModel() : ViewModel() {

    private val repository = MainRepository()

    fun loadDoctors(): LiveData<MutableList<DoctorModel>> {
        return repository.load()
    }
}