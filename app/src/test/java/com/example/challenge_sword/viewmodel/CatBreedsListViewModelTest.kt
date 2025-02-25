package com.example.challenge_sword.viewmodel

import com.example.challenge_sword.domain.model.CatBreed
import com.example.challenge_sword.data.repository.CatRepository
import com.example.challenge_sword.domain.interactor.FavouriteInteractor
import com.example.challenge_sword.ui.presentation.listscreen.CatBreedsListViewModel
import io.mockk.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.*
import org.junit.*
import org.junit.Assert.*

@OptIn(ExperimentalCoroutinesApi::class)
class CatBreedsListViewModelTest {

    private lateinit var viewModel: CatBreedsListViewModel
    private val catRepository: CatRepository = mockk()
    private lateinit var favouriteInteractor: FavouriteInteractor

    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        favouriteInteractor = mockk()
        viewModel = CatBreedsListViewModel(catRepository, favouriteInteractor)
    }

    @Test
    fun `fetchCatBreeds should update catResponseBreeds state and set isLoading to false`() =
        runBlocking {
            // Mockando os dados
            val catBreeds = listOf(
                CatBreed(
                    id = "1",
                    name = "Siamese",
                    imageUrl = "https://cdn2.thecatapi.com/images/siamese.jpg",
                    temperament = "",
                    origin = "",
                    description = "",
                    lifeSpan = "20"
                ),
                CatBreed(
                    id = "2",
                    name = "Maine Coon",
                    imageUrl = "https://cdn2.thecatapi.com/images/maine_coon.jpg",
                    temperament = "Gentle, Playful, Intelligent",
                    origin = "United States",
                    description = "The Maine Coon is known for its large size, friendly nature, and luxurious fur.",
                    lifeSpan = "12 - 15 years"
                )
            )

            every { catRepository.getCats() } returns flowOf(catBreeds)

            viewModel = CatBreedsListViewModel(catRepository, favouriteInteractor)

            assertEquals(catBreeds, viewModel.catResponseBreeds.value)
            assertFalse(viewModel.isLoading.value)
        }

    @Test
    fun `updateSearchQuery should update the search query state`() = runBlocking {
        val query = "Maine"
        viewModel.updateSearchQuery(query)

        assertEquals(query, viewModel.searchQuery.value)
    }

    @Test
    fun `toggleFavourite should add cat to favourites when not already favourite`() = runBlocking {
        val cat = CatBreed(
            id = "1",
            name = "Siamese",
            imageUrl = "https://cdn2.thecatapi.com/images/siamese.jpg",
            temperament = "",
            origin = "",
            description = "",
            lifeSpan = "20"
        )
        CatBreed(
            id = "2",
            name = "Maine Coon",
            imageUrl = "https://cdn2.thecatapi.com/images/maine_coon.jpg",
            temperament = "",
            origin = "",
            description = "",
            lifeSpan = "15 years"
        )

        every { favouriteInteractor.getFavouriteCats() } returns flowOf(emptyList())
        coEvery { favouriteInteractor.addFavouriteCat(cat) } just Runs
        viewModel.toggleFavourite(cat)
        coVerify { favouriteInteractor.addFavouriteCat(cat) }
    }

    @Test
    fun `toggleFavourite should remove cat from favourites when already favourite`() = runBlocking {
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
        viewModel.toggleFavourite(cat1)
        coVerify { favouriteInteractor.removeFavouriteCat(cat1) }
    }
}
