package com.example.superherowithcoroutines

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyViewModel : ViewModel() {

    private val _uiState = MutableLiveData<UiState>(UiState.Empty)
    val uiState: LiveData<UiState> = _uiState
    val repo = MyApplication.getApp().repo

    fun getData() {
        _uiState.value = UiState.Processing
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val superheroResponse = repo.getSuperhero()
                if (superheroResponse.isSuccessful) {
                    val data = superheroResponse.body()
                    _uiState.postValue(UiState.Result(data))
                } else {
                    _uiState.postValue(UiState.Error("Error code ${superheroResponse.code()}"))
                }
            } catch (e: Exception) {
                _uiState.postValue(UiState.Error(e.localizedMessage as String))
            }
        }
    }

    sealed class UiState {
        object Empty : UiState()
        object Processing : UiState()
        class Result(val superheroList: List<SuperheroResponse>?) : UiState()
        class Error(val description: String) : UiState()
    }
}