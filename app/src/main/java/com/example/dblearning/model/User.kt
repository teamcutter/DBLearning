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

// @Embedded позволяет нам представить экземпляр другого класса, который является свойством данного
// класса, как свойства данного класса в таблице БД
// например: data class Person(
// val name: String,
// @Embedded
// val address: Address <-- БД видит как val streetName: String, val streetNumber: Int
// )

// data class Address(
// val streetName: String,
// val streetNumber: Int
// )

// @TypeConverter
// https://www.youtube.com/watch?v=adGU0A80EJ0&list=PLSrm9z4zp4mEPOfZNV9O-crOhoMa0G2-o&index=7