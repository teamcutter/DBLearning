package com.example.dblearning.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

// т.к у нас @PrimaryKey(autoGenerate = true), это значит, одинаковых значений никогда не будет,
// даже после удаления полей из базы
// @Parcelize - предоставляет возможность передавать объекты класса User
// в качестве аргумента в активити/фрагменты (класс обязательно должен имплиментить Parcelable)
// @Entity - представляет таблицу в пределах БД
@Parcelize
@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val firstName: String,
    val lastName: String,
    val age: Int
) : Parcelable