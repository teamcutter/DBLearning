package com.example.dblearning.data

import androidx.room.Entity
import androidx.room.PrimaryKey

//@Entity - представляет таблицу в пределах БД
@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val firstName: String,
    val lastName: String,
    val age: Int
)