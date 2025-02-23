package com.example.challenge_sword.navigation

import CatBreedsDetailsScreen
import CatBreedsListScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.challenge_sword.ui.presentation.favouritescreen.CatBreedFavouriteScreen

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = "catBreedsList",
    ) {

        composable("catBreedsList") {
            CatBreedsListScreen(
                navController = navController
            )
        }
        composable("catBreedsDetails/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")
            id?.let {
                CatBreedsDetailsScreen(catId = it, onBackButtonClick = {
                    navController.popBackStack()
                })
            }
        }
        composable("catBreedFavouriteScreen") {
            CatBreedFavouriteScreen(
                navController = navController,
                onBackButtonClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}