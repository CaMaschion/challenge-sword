package com.example.challenge_sword.ui.presentation.favouritescreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.challenge_sword.domain.model.CatBreed
import com.example.challenge_sword.domain.interactor.FavouriteInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatBreedFavouriteViewModel @Inject constructor(
    private val favouriteInteractor: FavouriteInteractor,
) : ViewModel() {

    private val _catFavourite = MutableStateFlow<List<CatBreed>>(emptyList())
    val catFavourite: StateFlow<List<CatBreed>> get() = _catFavourite

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> get() = _isLoading


    init {
        viewModelScope.launch {
            fetchFavouriteCat()
        }
    }

    private suspend fun fetchFavouriteCat() {
        favouriteInteractor.getFavouriteCats()
            .catch { e ->
                Log.e("CatBreedsFavouriteViewModel", "Error fetching cat breeds", e)
                _isLoading.value = false
            }
            .collect { favourites ->
                _catFavourite.value = favourites
                _isLoading.value = false
            }
    }

    private fun insertFavouriteCat(cat: CatBreed) {
        viewModelScope.launch {
            favouriteInteractor.addFavouriteCat(cat)
            fetchFavouriteCat()
        }
    }

    private fun deleteFavouriteCat(cat: CatBreed) {
        viewModelScope.launch {
            favouriteInteractor.removeFavouriteCat(cat)
            fetchFavouriteCat()
        }
    }

    fun toggleFavourite(cat: CatBreed) {
        viewModelScope.launch {
            if (_catFavourite.value.contains(cat)) {
                deleteFavouriteCat(cat)
            } else {
                insertFavouriteCat(cat)
            }
        }
    }
}
