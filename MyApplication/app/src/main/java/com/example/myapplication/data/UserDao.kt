package com.example.myapplication.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTask(task: User)

    @Query("UPDATE Task SET class_num = :class_num, date = :date, type = :type WHERE class_num = :oldmyClass AND date = :olddate AND type = :oldtype")
    suspend fun updateUser(class_num: String, date: String, type: String, olddate: String,
                           oldmyClass: String, oldtype: String)

    @Query("DELETE FROM Task WHERE class_num = :oldmyClass AND date = :olddate AND type = :oldtype ")
    suspend fun DeleteUser(olddate: String,oldmyClass: String, oldtype: String)

    @Query("SELECT * FROM Task ORDER BY date ASC")
    fun filterByDate(): LiveData<List<User>>

    @Query("SELECT * FROM Task ORDER BY type ASC")
    fun filterByType(): LiveData<List<User>>

    @Query("SELECT * FROM Task ORDER BY class_num ASC")
    fun readAllData(): LiveData<List<User>>
}