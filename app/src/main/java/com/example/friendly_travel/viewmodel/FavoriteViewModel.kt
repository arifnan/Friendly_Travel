package com.example.friendly_travel.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.friendly_travel.data.WisataRepository
import com.example.friendly_travel.model.Wisata
import com.example.friendly_travel.state.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class FavoriteViewModel (
    private val repository: WisataRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<Wisata>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Wisata>>>
        get() = _uiState

    fun getFavoriteFilm() = viewModelScope.launch {
        repository.getFavoriteWisata()
            .catch {
                _uiState.value = UiState.Error(it.message.toString())
            }
            .collect {
                _uiState.value = UiState.Success(it)
            }
    }

    fun updatePlayer(id: Int, newState: Boolean) {
        repository.updateWisata(id, newState)
        getFavoriteFilm()
    }
}