package com.example.challenge_sword.ui.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.challenge_sword.data.model.FavouriteResponse
import com.example.challenge_sword.data.repository.CatRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

class CatBreedFavouriteViewModel @Inject constructor(
    private val catRepository: CatRepository
) : ViewModel() {

    private val _catFavouriteResponse = MutableStateFlow<List<FavouriteResponse>>(emptyList())
    val catFavouriteResponse: StateFlow<List<FavouriteResponse>> get() = _catFavouriteResponse

    private val subId = "4706dc5f-9fd7-4952-bb74-8d85687f47ba"

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    val favouritesCats: Flow<List<FavouriteResponse>> = _catFavouriteResponse

    init {
        fetchFavouriteCat()
    }

    private fun fetchFavouriteCat() {
        viewModelScope.launch {
            catRepository.getFavouriteCats(subId)
                .catch { e ->
                    Log.e("CatBreedsFavouriteViewModel", "Error fetching cat breeds", e)
                    _isLoading.value = false
                }
                .collect { cats ->
                    _catFavouriteResponse.value = cats
                    _isLoading.value = false
                }
        }
    }
}
