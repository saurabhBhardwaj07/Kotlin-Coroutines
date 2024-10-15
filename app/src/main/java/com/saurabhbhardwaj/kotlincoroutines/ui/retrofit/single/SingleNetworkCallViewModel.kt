package com.saurabhbhardwaj.kotlincoroutines.ui.retrofit.single

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.saurabhbhardwaj.kotlincoroutines.data.api.ApiHelper
import com.saurabhbhardwaj.kotlincoroutines.data.local.DatabaseHelper
import com.saurabhbhardwaj.kotlincoroutines.data.model.ApiUser
import com.saurabhbhardwaj.kotlincoroutines.ui.base.UiState



class SingleNetworkCallViewModel (
    private val apiHelper: ApiHelper,
    private val dbHelper: DatabaseHelper
) : ViewModel() {

    private val uiState = MutableLiveData<UiState<List<ApiUser>>>()

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            uiState.postValue(UiState.Loading)
            try {
                val usersFromApi = apiHelper.getUsers()
                uiState.postValue(UiState.Success(usersFromApi))
            } catch (e: Exception) {
                uiState.postValue(UiState.Error(e.toString()))
            }
        }
    }

    fun getUiState(): LiveData<UiState<List<ApiUser>>> {
        return uiState
    }

}