package com.saurabhbhardwaj.kotlincoroutines.data.local

import com.saurabhbhardwaj.kotlincoroutines.data.local.entity.User

interface DatabaseHelper {

    suspend fun getUsers(): List<User>

    suspend fun insertAll(users: List<User>)

}