package com.example.dblearning.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.dblearning.model.User

//Dao - "прослойка" между БД и программой, содержит методы для доступа к БД
@Dao
interface UserDao {

    // suspend is using for coroutines
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("DELETE FROM user_table")
    suspend fun deleteAllUsers()

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<User>>

    @Query("SELECT * FROM user_table WHERE firstName LIKE :searchQuery OR lastName LIKE :searchQuery")
    fun searchDatabase(searchQuery: String): LiveData<List<User>>
}