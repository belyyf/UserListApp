package com.example.userlistapp.repository

import androidx.lifecycle.LiveData
import com.example.userlistapp.data.User
import com.example.userlistapp.data.UserDao

class UserRepository(private val userDao: UserDao) {
    val allUsers: LiveData<List<User>> = userDao.getAllUsers()

    suspend fun insert(user: User) {
        userDao.insert(user)
    }

    suspend fun delete(user: User) {
        userDao.delete(user)
    }
}
