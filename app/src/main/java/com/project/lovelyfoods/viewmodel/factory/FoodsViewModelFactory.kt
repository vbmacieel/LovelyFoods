package com.project.lovelyfoods.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.project.lovelyfoods.data.repository.FirebaseRepository
import com.project.lovelyfoods.viewmodel.FoodsDetailViewModel
import com.project.lovelyfoods.viewmodel.FoodsFormViewModel
import com.project.lovelyfoods.viewmodel.FoodsGenerateViewModel
import com.project.lovelyfoods.viewmodel.FoodsListViewModel

class FoodsViewModelFactory : ViewModelProvider.Factory {
    private val repository = FirebaseRepository()

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when (modelClass) {
            FoodsFormViewModel::class.java -> FoodsFormViewModel(repository) as T
            FoodsListViewModel::class.java -> FoodsListViewModel(repository) as T
            FoodsGenerateViewModel::class.java -> FoodsGenerateViewModel(repository) as T
            FoodsDetailViewModel::class.java -> FoodsDetailViewModel(repository) as T
            else -> throw IllegalArgumentException("Cannot find this ViewModel")
        }
    }
}