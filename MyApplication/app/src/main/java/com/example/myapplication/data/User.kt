package com.example.myapplication.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Task")
data class User(
    @PrimaryKey(autoGenerate = false)
    val class_num: String,
    val date: String,
    val type: String?
)