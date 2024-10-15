package com.saurabhbhardwaj.kotlincoroutines.ui.errorhandling.exceptionhandler

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import com.saurabhbhardwaj.kotlincoroutines.data.api.ApiHelper
import com.saurabhbhardwaj.kotlincoroutines.data.local.DatabaseHelper
import com.saurabhbhardwaj.kotlincoroutines.data.model.ApiUser
import com.saurabhbhardwaj.kotlincoroutines.ui.base.UiState

class ExceptionHandlerViewModel(
    private val apiHelper: ApiHelper, private val dbHelper: DatabaseHelper
) : ViewModel() {

    private val uiState = MutableLiveData<UiState<List<ApiUser>>>()

    private val exceptionHandler = CoroutineExceptionHandler { _, e ->
        uiState.postValue(UiState.Error("exception handler: $e"))
    }

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch(exceptionHandler) {
            uiState.postValue(UiState.Loading)
            val usersFromApi = apiHelper.getUsersWithError()
            uiState.postValue(UiState.Success(usersFromApi))
        }
    }

    fun getUiState(): LiveData<UiState<List<ApiUser>>> {
        return uiState
    }

}