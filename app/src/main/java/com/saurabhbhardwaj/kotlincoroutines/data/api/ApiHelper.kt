package com.saurabhbhardwaj.kotlincoroutines.data.api

import com.saurabhbhardwaj.kotlincoroutines.data.model.ApiUser

interface ApiHelper {

    suspend fun getUsers(): List<ApiUser>

    suspend fun getMoreUsers(): List<ApiUser>

    suspend fun getUsersWithError(): List<ApiUser>

}