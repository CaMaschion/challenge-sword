package com.example.challenge_sword.ui.presentation.favouritescreen

import android.util.Log
import androidx.compose.runtime.mutableStateMapOf
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

    private val _favouriteCats = mutableStateMapOf<String, Boolean>()
    val favouriteCats: Map<String, Boolean> get() = _favouriteCats

    init {
        fetchFavouriteCat()
    }

    private fun fetchFavouriteCat() {
        viewModelScope.launch {
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
    }

    private fun removeFavouriteCat(cat: CatBreed) {
        viewModelScope.launch {
            _catFavourite.value = _catFavourite.value.filter { it.id != cat.id }
        }
    }

    fun toggleFavourite(cat: CatBreed) {
        viewModelScope.launch {
            val isFavourite = _favouriteCats[cat.id] ?: true
            if (isFavourite) {
                favouriteInteractor.removeFavouriteCat(cat.id)
                removeFavouriteCat(cat)
            } else {
                favouriteInteractor.addFavouriteCat(cat.id)
            }
            _favouriteCats[cat.id] = !isFavourite
        }
    }
}
