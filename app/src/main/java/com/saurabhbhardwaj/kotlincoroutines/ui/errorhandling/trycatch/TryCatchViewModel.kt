package com.saurabhbhardwaj.kotlincoroutines.ui.errorhandling.trycatch

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.saurabhbhardwaj.kotlincoroutines.data.api.ApiHelper
import com.saurabhbhardwaj.kotlincoroutines.data.local.DatabaseHelper
import com.saurabhbhardwaj.kotlincoroutines.data.model.ApiUser
import com.saurabhbhardwaj.kotlincoroutines.ui.base.UiState

class TryCatchViewModel(
    private val apiHelper: ApiHelper, private val dbHelper: DatabaseHelper
) : ViewModel() {

    private val uiState = MutableLiveData<UiState<List<ApiUser>>>()

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            uiState.postValue(UiState.Loading)
            try {
                val usersFromApi = apiHelper.getUsersWithError()
                uiState.postValue(UiState.Success(usersFromApi))
            } catch (e: Exception) {
                uiState.postValue(UiState.Error("Something Went Wrong"))
            }
        }
    }

    fun getUiState(): LiveData<UiState<List<ApiUser>>> {
        return uiState
    }

}