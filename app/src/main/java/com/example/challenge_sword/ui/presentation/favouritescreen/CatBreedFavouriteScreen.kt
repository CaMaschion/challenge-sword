package com.example.challenge_sword.ui.presentation.favouritescreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.challenge_sword.R
import com.example.challenge_sword.ui.components.CatBreedCardComponent
import com.example.challenge_sword.ui.components.CatBreedTopBarComponent

@Composable
fun CatBreedFavouriteScreen(
    navController: NavController,
    onBackButtonClick: () -> Unit
) {

    val viewModel: CatBreedFavouriteViewModel = hiltViewModel()
    val catFavourite by viewModel.catFavourite.collectAsState(initial = emptyList())
    val isLoading by viewModel.isLoading.collectAsState()
    val listState = rememberLazyGridState()

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
                .padding(top = 16.dp)
        ) {

            CatBreedTopBarComponent(
                title = stringResource(id = R.string.cat_favourites),
                showBackButton = true,
                onBackButtonClick = onBackButtonClick
            )

            if (isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }

            } else {
                LazyVerticalGrid(
                    state = listState,
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.weight(1f),
                    contentPadding = PaddingValues(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(catFavourite) { cat ->
                        CatBreedCardComponent(
                            showLifeSpan = true,
                            cat = cat,
                            isFavourite = true,
                            onClick = {
                                navController.navigate("catBreedsDetails/${cat.id}")
                            }
                        )
                    }
                }
            }
        }
    }
}