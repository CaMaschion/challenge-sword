package com.example.challenge_sword.ui.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.challenge_sword.data.model.CatResponse
import com.example.challenge_sword.data.repository.CatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CatBreedsViewModel @Inject constructor(
    private val catRepository: CatRepository
) : ViewModel() {

    private val _catResponseBreeds = MutableStateFlow<List<CatResponse>>(emptyList())
    val catResponseBreeds: StateFlow<List<CatResponse>> get() = _catResponseBreeds

    init {
        fetchCatBreeds()
    }

    private fun fetchCatBreeds() {
        viewModelScope.launch {
            catRepository.getCats()
                .catch { e ->
                    Log.e("CatBreedsViewModel", "Error fetching cat breeds", e)
                }
                .collect { cats ->
                    _catResponseBreeds.value = cats
                }
        }
    }
}