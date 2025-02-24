package com.example.challenge_sword.ui.presentation.detailscreen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.challenge_sword.domain.model.CatBreed
import com.example.challenge_sword.data.repository.CatRepository
import com.example.challenge_sword.domain.interactor.FavouriteInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatBreedsDetailsViewModel @Inject constructor(
    private val catRepository: CatRepository,
    private val favouriteInteractor: FavouriteInteractor
) : ViewModel() {

    private val _selectedCatBreed = mutableStateOf<CatBreed?>(null)
    val selectedCatBreed: State<CatBreed?> = _selectedCatBreed

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    private var isFavourite = false

    private fun checkFavouriteCat(catId: String) {
        viewModelScope.launch {
            favouriteInteractor.getFavouriteCatById(catId)
                .catch { e ->
                    Log.e("CatBreedsDetailsViewModel", "Error fetching favourite cat", e)
                }
                .collect { cat ->
                    isFavourite = cat != null
                }
        }
    }

    fun getSelectedCatBreed(catId: String) {
        viewModelScope.launch {
            catRepository.getCatById(catId)
                .catch { e ->
                    Log.e("CatBreedsDetailsViewModel", "Error fetching cat breeds", e)
                    _isLoading.value = false
                }
                .collect { cat ->
                    if (cat != null) {
                        checkFavouriteCat(catId)
                        cat.isFavourite = isFavourite
                    }
                    _selectedCatBreed.value = cat
                    _isLoading.value = false
                }
        }
    }

    fun toggleFavourite(cat: CatBreed) {
        viewModelScope.launch {
            if (isFavourite) {
                favouriteInteractor.removeFavouriteCat(cat)
            } else {
                favouriteInteractor.addFavouriteCat(cat)
            }
            isFavourite = !isFavourite
        }
    }
}
