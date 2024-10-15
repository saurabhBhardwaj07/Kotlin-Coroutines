package com.saurabhbhardwaj.kotlincoroutines.data.api

import javax.inject.Inject

class ApiHelperImpl @Inject constructor (private val apiService: ApiService) : ApiHelper {

    override suspend fun getUsers() = apiService.getUsers()

    override suspend fun getMoreUsers() = apiService.getMoreUsers()

    override suspend fun getUsersWithError() = apiService.getUsersWithError()

}