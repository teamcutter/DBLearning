package com.example.dblearning.repository

import androidx.lifecycle.LiveData
import com.example.dblearning.data.UserDao
import com.example.dblearning.model.User

// repo class abstracts access to multiple data sources.
// the repo is not a part of the Architecture Components libraries,
// but is a suggested best practice for code separation and architecture.

class UserRepository(private val userDao: UserDao) {

    val readAllData: LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User) {
        userDao.addUser(user)
    }

    suspend fun updateUser(user: User) {
        userDao.updateUser(user)
    }
}