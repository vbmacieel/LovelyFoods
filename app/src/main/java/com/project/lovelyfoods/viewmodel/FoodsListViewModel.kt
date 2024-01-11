package com.project.lovelyfoods.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.lovelyfoods.data.repository.FirebaseRepository
import com.project.lovelyfoods.model.Food

class FoodsListViewModel(private val repository: FirebaseRepository) : ViewModel() {
    private val _foodList = MutableLiveData<List<Food>>()
    private val _errorMessage = MutableLiveData<String>()
    val foodList: LiveData<List<Food>> = _foodList
    val errorMessage: LiveData<String> = _errorMessage

    fun getAllFoods() {
        repository.getAllFoods(
            onSuccess = { foods ->
                _foodList.postValue(foods)
            },
            onFailure = { exception ->
                _errorMessage.postValue(exception.message)
            })
    }
}