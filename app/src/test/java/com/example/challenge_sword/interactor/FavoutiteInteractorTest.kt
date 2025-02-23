package com.example.challenge_sword.interactor
import com.example.challenge_sword.data.repository.CatRepository
import com.example.challenge_sword.domain.interactor.FavouriteInteractor
import com.example.challenge_sword.domain.interactor.FavouriteInteractorImpl
import com.example.challenge_sword.domain.model.CatBreed
import io.mockk.*
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class FavouriteInteractorTest {

    private lateinit var favouriteInteractor: FavouriteInteractor
    private val catRepository: CatRepository = mockk()

    private val testSubId = "4706dc5f-9fd7-4952-bb74-8d85687f47ba"

    @Before
    fun setUp() {
        favouriteInteractor = FavouriteInteractorImpl(catRepository)
    }

    @Test
    fun `getFavouriteCats should return list of CatBreed`() = runBlocking {
        // Mock
        val favouriteCats = listOf(mockk<CatBreed> {
            every { id } returns "1"
        }, mockk<CatBreed> {
            every { id } returns "2"
        })

        val catBreed1 = mockk<CatBreed> {
            every { id } returns "1"
            every { name } returns "Maine Coon"
        }

        val catBreed2 = mockk<CatBreed> {
            every { id } returns "2"
            every { name } returns "Siamese"
        }

        coEvery { catRepository.getFavouriteCats(testSubId) } returns flowOf(favouriteCats)
        coEvery { catRepository.getCatById("1") } returns flowOf(catBreed1)
        coEvery { catRepository.getCatById("2") } returns flowOf(catBreed2)

        val result = mutableListOf<List<CatBreed>>()
        favouriteInteractor.getFavouriteCats().collect{ result.add(it)}

        assertEquals(1, 1)
        assertEquals(2, result[0].size)
        assertTrue(result[0].contains(catBreed1))
        assertTrue(result[0].contains(catBreed2))
    }

    @Test
    fun `addFavouriteCat should call repository with correct parameters`() = runBlocking {
        val catId = "cat123"

        coEvery { catRepository.addFavouriteCat(testSubId, catId) } just Runs

        favouriteInteractor.addFavouriteCat(catId)

        coVerify { catRepository.addFavouriteCat(testSubId, catId) }
    }

    @Test
    fun `removeFavouriteCat should call repository with correct favouriteId`() = runBlocking {
        val favouriteId = "fav456"

        coEvery { catRepository.removeFavouriteCat(favouriteId) } just Runs

        favouriteInteractor.removeFavouriteCat(favouriteId)

        coVerify { catRepository.removeFavouriteCat(favouriteId) }
    }
}
