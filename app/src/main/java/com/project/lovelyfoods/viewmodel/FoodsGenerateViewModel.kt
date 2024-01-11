package com.project.lovelyfoods.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.lovelyfoods.data.repository.FirebaseRepository
import com.project.lovelyfoods.model.Food

class FoodsGenerateViewModel(private val repository: FirebaseRepository) : ViewModel() {
    private val _food = MutableLiveData<Food>()
    private val _errorMessage = MutableLiveData<String>()
    val food: LiveData<Food> = _food
    val errorMessage: LiveData<String> = _errorMessage

    fun generateRandomFood() {
        repository.getRandomFood(onSuccess = {food ->
            _food.postValue(food)
        }, onFailure = {exception ->
                _errorMessage.postValue(exception.message)
        })
    }
}