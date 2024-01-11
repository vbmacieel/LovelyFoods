package com.project.lovelyfoods.viewmodel

import androidx.lifecycle.ViewModel
import com.project.lovelyfoods.data.repository.FirebaseRepository

class FoodsFormViewModel(private val repository: FirebaseRepository) : ViewModel() {

    fun addNewFood(name: String, url: String, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        repository.addNewFood(name, url, onSuccess, onFailure)
    }
}