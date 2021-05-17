package com.example.myapplication.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<User>>
    val filterByDate: LiveData<List<User>>
    val filterByType: LiveData<List<User>>

    private val repository: UserRepository
    init{
        val userDao = UserDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        readAllData = repository.readAllData
        filterByDate = repository.filterByDate
        filterByType = repository.filterByType
    }
    fun addUser(user: User){
        viewModelScope.launch(Dispatchers.IO){
            repository.addUser(user)
        }
    }
    fun updateUser(class_num: String, date: String, type: String, olddate: String,
                   oldmyClass: String, oldtype: String){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateUser(class_num, date, type, olddate, oldmyClass, oldtype)
        }
    }

    fun DeleteUser(olddate: String,oldmyClass: String, oldtype: String){
        viewModelScope.launch(Dispatchers.IO) {
            repository.DeleteUser(olddate, oldmyClass, oldtype)
        }
    }

}