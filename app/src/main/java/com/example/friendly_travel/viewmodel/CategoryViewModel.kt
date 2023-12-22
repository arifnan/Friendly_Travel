package com.example.friendly_travel.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.friendly_travel.data.WisataRepository
import com.example.friendly_travel.model.Wisata
import com.example.friendly_travel.state.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CategoryViewModel (
    private val repository: WisataRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<Wisata>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<Wisata>>
        get() = _uiState

    fun getWisataById(id: Int) = viewModelScope.launch {
        _uiState.value = UiState.Loading
        _uiState.value = UiState.Success(repository.getWisataById(id))
    }


    fun updateWisata(id: Int, newState: Boolean) = viewModelScope.launch {
        repository.updateWisata(id, !newState)
            .collect { isUpdated ->
                if (isUpdated) getWisataById(id)
            }
    }
}