package com.example.flologin.data.repository

import com.example.flologin.data.local.dao.UserDao
import com.example.flologin.data.local.entity.User

class UserRepository(
    private val dao: UserDao
) {

    suspend fun insert(user: User) {
        dao.insert(user)
    }

    suspend fun login(
        username: String,
        password: String
    ): User? {
        return dao.login(username, password)
    }
}