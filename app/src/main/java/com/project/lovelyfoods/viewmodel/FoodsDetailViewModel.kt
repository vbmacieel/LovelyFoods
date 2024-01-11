package com.project.lovelyfoods.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.lovelyfoods.data.repository.FirebaseRepository
import com.project.lovelyfoods.model.Food

class FoodsDetailViewModel(private val repository: FirebaseRepository): ViewModel() {
    private val _statusMessage = MutableLiveData<String>()
    private val _food = MutableLiveData<Food>()
    val food: LiveData<Food> = _food
    val statusMessage: LiveData<String> = _statusMessage

    fun deleteFood(food: Food) {
        repository.deleteFood(food.id,
            onSuccess = {
                _statusMessage.postValue("Food deleted successfully!")
            },
            onFailure = {
                _statusMessage.postValue(it.message)
            })
    }

    fun getFood(id: String) {
        repository.getFood(id,
            onSuccess = {
                _food.postValue(it)
            },
            onFailure = {
                _statusMessage.postValue(it.message)
            })
    }
}