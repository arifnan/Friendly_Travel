package com.example.friendly_travel.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.friendly_travel.data.WisataRepository
import com.example.friendly_travel.model.Wisata
import com.example.friendly_travel.state.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: WisataRepository

) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<Wisata>>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Wisata>>>
        get() = _uiState

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    fun search(newQuery: String) = viewModelScope.launch {
        _query.value = newQuery
        repository.searchWisata(_query.value)
            .catch {
                _uiState.value = UiState.Error(it.message.toString())
            }
            .collect {
                _uiState.value = UiState.Success(it)
            }
    }

    fun updateWisata(id: Int, newState: Boolean) = viewModelScope.launch {
        repository.updateWisata(id, newState)
            .collect { isUpdated ->
                if (isUpdated) search(_query.value)
            }
    }
}