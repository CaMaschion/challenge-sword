package com.example.challenge_sword.ui.presentation.listscreen

import android.util.Log
import androidx.compose.runtime.mutableStateMapOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.challenge_sword.domain.model.CatBreed
import com.example.challenge_sword.data.repository.CatRepository
import com.example.challenge_sword.domain.interactor.FavouriteInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatBreedsListViewModel @Inject constructor(
    private val catRepository: CatRepository,
    private val favouriteInteractor: FavouriteInteractor
) : ViewModel() {

    private val _catResponseBreeds = MutableStateFlow<List<CatBreed>>(emptyList())
    val catResponseBreeds: StateFlow<List<CatBreed>> get() = _catResponseBreeds

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> get() = _searchQuery

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    private val _favouriteCats = mutableStateMapOf<String, Boolean>()
    val favouriteCats: Map<String, Boolean> get() = _favouriteCats

    val filteredBreeds: Flow<List<CatBreed>> = combine(
        _catResponseBreeds,
        _searchQuery
    ) { breeds, query ->
        if (query.isEmpty()) {
            breeds
        } else {
            breeds.filter { breed ->
                breed.name.contains(query, ignoreCase = true)
            }
        }
    }

    init {
        fetchCatBreeds()
    }

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    private fun fetchCatBreeds() {
        viewModelScope.launch {
            catRepository.getCats()
                .catch { e ->
                    Log.e("CatBreedsViewModel", "Error fetching cat breeds", e)
                    _isLoading.value = false
                }
                .collect { cat ->
                    _catResponseBreeds.value = cat
                    _isLoading.value = false
                }
        }
    }

    private fun insertFavouriteCat(cat: CatBreed) {
        viewModelScope.launch {
            favouriteInteractor.addFavouriteCat(cat)
            fetchCatBreeds()
        }
    }

    private fun deleteFavouriteCat(cat: CatBreed) {
        viewModelScope.launch {
            favouriteInteractor.removeFavouriteCat(cat)
            fetchCatBreeds()
        }
    }

    fun toggleFavourite(cat: CatBreed) {
        viewModelScope.launch {
            val isFavourite = _favouriteCats[cat.id] ?: false
            if (isFavourite) {
                deleteFavouriteCat(cat)
            } else {
                insertFavouriteCat(cat)
            }
        }
    }
}
