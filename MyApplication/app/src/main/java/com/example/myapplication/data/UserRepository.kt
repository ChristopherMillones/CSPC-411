package com.example.myapplication.data

import androidx.lifecycle.LiveData

class UserRepository(private val userDao: UserDao) {

    val readAllData: LiveData<List<User>> = userDao.readAllData()
    val filterByDate: LiveData<List<User>> = userDao.filterByDate()
    val filterByType: LiveData<List<User>> = userDao.filterByType()


    suspend fun addUser(user: User){
        userDao.addTask(user)
    }


    suspend fun updateUser(class_num: String, date: String, type: String, olddate: String,
                           oldmyClass: String, oldtype: String){
        userDao.updateUser(class_num, date, type, olddate, oldmyClass, oldtype)
    }

    suspend fun DeleteUser(olddate: String,oldmyClass: String, oldtype: String){
        userDao.DeleteUser(olddate, oldmyClass, oldtype)
    }



}