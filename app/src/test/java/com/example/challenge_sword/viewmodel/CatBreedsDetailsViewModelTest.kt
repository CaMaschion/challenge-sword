package com.example.challenge_sword.viewmodel

import com.example.challenge_sword.data.repository.CatRepository
import com.example.challenge_sword.domain.interactor.FavouriteInteractor
import com.example.challenge_sword.domain.model.CatBreed
import com.example.challenge_sword.ui.presentation.detailscreen.CatBreedsDetailsViewModel
import io.mockk.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.*
import org.junit.*

class CatBreedsDetailsViewModelTest {

    private lateinit var viewModel: CatBreedsDetailsViewModel
    private val catRepository: CatRepository = mockk()
    private val favouriteInteractor: FavouriteInteractor = mockk()
    private val testDispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = CatBreedsDetailsViewModel(catRepository, favouriteInteractor)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `toggleFavourite should remove cat when it is favourite`() = runTest {
        // Given
        val cat1 = CatBreed(
            id = "1",
            name = "Siamese",
            imageUrl = "https://cdn2.thecatapi.com/images/siamese.jpg",
            temperament = "",
            origin = "",
            description = "",
            lifeSpan = "20"
        )

        coEvery { favouriteInteractor.getFavouriteCats() } returns flowOf(listOf(cat1))
        coEvery { favouriteInteractor.removeFavouriteCat(cat1) } just Runs

        viewModel.fetchFavouriteCats()
        advanceUntilIdle()
        viewModel.toggleFavourite(cat1)
        advanceUntilIdle()

        coVerify { favouriteInteractor.removeFavouriteCat(cat1) }
        verify { favouriteInteractor.getFavouriteCats() }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `toggleFavourite should add cat when it is not favourite`() = runTest {
        val cat1 = CatBreed(
            id = "1",
            name = "Siamese",
            imageUrl = "https://cdn2.thecatapi.com/images/siamese.jpg",
            temperament = "",
            origin = "",
            description = "",
            lifeSpan = "20"
        )


        coEvery { favouriteInteractor.getFavouriteCats() } returns flowOf(emptyList())
        coEvery { favouriteInteractor.addFavouriteCat(cat1) } just Runs

        viewModel.fetchFavouriteCats()
        advanceUntilIdle()
        viewModel.toggleFavourite(cat1)
        advanceUntilIdle()

        coVerify { favouriteInteractor.addFavouriteCat(cat1) }
        verify { favouriteInteractor.getFavouriteCats() }
    }
}