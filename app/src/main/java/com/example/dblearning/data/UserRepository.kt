package com.example.dblearning.data

import androidx.lifecycle.LiveData

// repo class abstracts access to multiple data sources.
// the repo is not a part of the Architecture Components libraries,
// but is a suggested best practice for code separation and architecture.

class UserRepository(private val userDao: UserDao) {

    val readAllData: LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User) {
        userDao.addUser(user)
    }
}