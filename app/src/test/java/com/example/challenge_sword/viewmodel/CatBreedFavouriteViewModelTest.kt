package com.example.challenge_sword.viewmodel

import com.example.challenge_sword.domain.model.CatBreed
import com.example.challenge_sword.domain.interactor.FavouriteInteractor
import com.example.challenge_sword.ui.presentation.favouritescreen.CatBreedFavouriteViewModel
import io.mockk.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.*
import org.junit.*
import org.junit.Assert.*

@OptIn(ExperimentalCoroutinesApi::class)
class CatBreedFavouriteViewModelTest {

    private lateinit var viewModel: CatBreedFavouriteViewModel
    private val favouriteInteractor: FavouriteInteractor = mockk()
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        every { favouriteInteractor.getFavouriteCats() } returns flowOf(emptyList())
        viewModel = CatBreedFavouriteViewModel(favouriteInteractor)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `fetchFavouriteCat should update catFavourite state and set isLoading to false`() = runTest {
        val catBreeds = listOf(
            CatBreed(
                id = "1",
                name = "Siamese",
                imageUrl = "https://cdn2.thecatapi.com/images/siamese.jpg",
                temperament = "",
                origin = "",
                description = "",
                lifeSpan = "20"
            )
        )

        every { favouriteInteractor.getFavouriteCats() } returns flowOf(catBreeds)
        viewModel = CatBreedFavouriteViewModel(favouriteInteractor)
        advanceUntilIdle()

        assertEquals(catBreeds, viewModel.catFavourite.value)
        assertFalse(viewModel.isLoading.value)
    }

    @Test
    fun `toggleFavourite should add cat to favourites when not already favourite`() = runTest {
        val cat = CatBreed(
            id = "1",
            name = "Siamese",
            imageUrl = "https://cdn2.thecatapi.com/images/siamese.jpg",
            temperament = "",
            origin = "",
            description = "",
            lifeSpan = "20"
        )

        coEvery { favouriteInteractor.addFavouriteCat(cat) } just Runs
        every { favouriteInteractor.getFavouriteCats() } returns flowOf(emptyList())

        viewModel.toggleFavourite(cat)
        advanceUntilIdle()

        coVerify { favouriteInteractor.addFavouriteCat(cat) }
    }

    @Test
    fun `toggleFavourite should remove cat from favourites when already favourite`() = runTest {
        val cat = CatBreed(
            id = "1",
            name = "Siamese",
            imageUrl = "https://cdn2.thecatapi.com/images/siamese.jpg",
            temperament = "",
            origin = "",
            description = "",
            lifeSpan = "20"
        )

        coEvery { favouriteInteractor.removeFavouriteCat(cat) } just Runs
        every { favouriteInteractor.getFavouriteCats() } returns flowOf(listOf(cat))

        viewModel = CatBreedFavouriteViewModel(favouriteInteractor)
        advanceUntilIdle()

        viewModel.toggleFavourite(cat)
        advanceUntilIdle()

        coVerify { favouriteInteractor.removeFavouriteCat(cat) }
    }
}