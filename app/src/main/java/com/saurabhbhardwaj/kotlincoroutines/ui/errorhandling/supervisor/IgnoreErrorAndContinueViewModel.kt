package com.saurabhbhardwaj.kotlincoroutines.ui.errorhandling.supervisor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import com.saurabhbhardwaj.kotlincoroutines.data.api.ApiHelper
import com.saurabhbhardwaj.kotlincoroutines.data.local.DatabaseHelper
import com.saurabhbhardwaj.kotlincoroutines.data.model.ApiUser
import com.saurabhbhardwaj.kotlincoroutines.ui.base.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class IgnoreErrorAndContinueViewModel @Inject constructor (
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
            // supervisorScope is needed, so that we can ignore error and continue
            // here, more than two child jobs are running in parallel under a supervisor, one child job gets failed, we can continue with other.
            supervisorScope {
                val usersFromApiDeferred = async { apiHelper.getUsersWithError() }
                val moreUsersFromApiDeferred = async { apiHelper.getMoreUsers() }

                val usersFromApi = try {
                    usersFromApiDeferred.await()
                } catch (e: Exception) {
                    emptyList()
                }

                val moreUsersFromApi = try {
                    moreUsersFromApiDeferred.await()
                } catch (e: Exception) {
                    emptyList()
                }

                val allUsersFromApi = mutableListOf<ApiUser>()
                allUsersFromApi.addAll(usersFromApi)
                allUsersFromApi.addAll(moreUsersFromApi)

                uiState.postValue(UiState.Success(allUsersFromApi))
            }
        }
    }

    fun getUiState(): LiveData<UiState<List<ApiUser>>> {
        return uiState
    }

}