package com.example.challenge_sword.interactor

import com.example.challenge_sword.data.repository.CatRepository
import com.example.challenge_sword.domain.interactor.FavouriteInteractor
import com.example.challenge_sword.domain.interactor.FavouriteInteractorImpl
import com.example.challenge_sword.domain.model.CatBreed
import io.mockk.*
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class FavouriteInteractorTest {

    private lateinit var favouriteInteractor: FavouriteInteractor
    private val catRepository: CatRepository = mockk()

    @Before
    fun setUp() {
        favouriteInteractor = FavouriteInteractorImpl(catRepository)
    }

    @Test
    fun `getFavouriteCats should return list of CatBreed`() = runBlocking {
        // Mock dos dados
        val favouriteCats = listOf(
            CatBreed(
                id = "1",
                name = "Maine Coon",
                imageUrl = "https://cdn2.thecatapi.com/images/maine_coon.jpg",
                temperament = "",
                origin = "",
                description = "",
                lifeSpan = "15 years"
            ),
            CatBreed(
                id = "2",
                name = "Siamese",
                imageUrl = "https://cdn2.thecatapi.com/images/siamese.jpg",
                temperament = "",
                origin = "",
                description = "",
                lifeSpan = "20"
            )
        )

        every { catRepository.getFavouriteCats() } returns flowOf(favouriteCats)

        val result = mutableListOf<List<CatBreed>>()
        favouriteInteractor.getFavouriteCats().toList(result)

        assertEquals(1, result.size)
        assertEquals(2, result[0].size)
        assertEquals("Maine Coon", result[0][0].name)
        assertEquals("Siamese", result[0][1].name)
    }

    @Test
    fun `getFavouriteCatById should return correct CatBreed`() = runBlocking {
        val catId = "123"
        val catBreed = CatBreed(
            id = catId,
            name = "Persian",
            imageUrl = "https://cdn2.thecatapi.com/images/persian.jpg",
            temperament = "",
            origin = "",
            description = "",
            lifeSpan = "17 years"
        )

        every { catRepository.getFavouriteCatById(catId) } returns flowOf(catBreed)
        val result = favouriteInteractor.getFavouriteCatById(catId).first()

        assertNotNull(result)
        assertEquals(catId, result?.id)
        assertEquals("Persian", result?.name)
    }

    @Test
    fun `addFavouriteCat should call repository with correct parameters`() = runBlocking {
        val cat = CatBreed(
            id = "cat123",
            name = "Bengal",
            imageUrl = "https://cdn2.thecatapi.com/images/bengal.jpg",
            temperament = "",
            origin = "",
            description = "",
            lifeSpan = "16 years"
        )

        coEvery { catRepository.insertFavouriteCat(any()) } just Runs
        favouriteInteractor.addFavouriteCat(cat)
        coVerify { catRepository.insertFavouriteCat(match { it.id == "cat123" }) }
    }

    @Test
    fun `removeFavouriteCat should call repository with correct favouriteId`() = runBlocking {
        val cat = CatBreed(
            id = "fav456",
            name = "Sphynx",
            imageUrl = "https://cdn2.thecatapi.com/images/sphynx.jpg",
            temperament = "",
            origin = "",
            description = "",
            lifeSpan = "14 years"
        )

        coEvery { catRepository.deleteFavouriteCat(any()) } just Runs
        favouriteInteractor.removeFavouriteCat(cat)
        coVerify { catRepository.deleteFavouriteCat(match { it.id == "fav456" }) }
    }
}
