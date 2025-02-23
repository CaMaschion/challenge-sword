import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
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
import com.example.challenge_sword.ui.components.CatBreedNotFoundComponent
import com.example.challenge_sword.ui.components.CatBreedTopBarComponent
import com.example.challenge_sword.ui.presentation.listscreen.CatBreedsListViewModel

@Composable
fun CatBreedsListScreen(
    navController: NavController,
) {

    val viewModel: CatBreedsListViewModel = hiltViewModel()
    val filteredBreeds by viewModel.filteredBreeds.collectAsState(initial = emptyList())
    val searchQuery by viewModel.searchQuery.collectAsState()
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
                title = stringResource(id = R.string.cat_breeds),
                showBackButton = false
            )

            OutlinedTextField(
                value = searchQuery,
                onValueChange = { viewModel.updateSearchQuery(it) },
                label = { Text("Search") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
            )

            when {
                isLoading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                filteredBreeds.isEmpty() -> {
                    CatBreedNotFoundComponent(
                        message = stringResource(id = R.string.cat_not_found),
                        showBackButton = false
                    )
                }

                else -> {
                    LazyVerticalGrid(
                        state = listState,
                        columns = GridCells.Fixed(2),
                        modifier = Modifier.weight(1f),
                        contentPadding = PaddingValues(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(filteredBreeds) { cat ->
                            CatBreedCardComponent(
                                cat = cat,
                                onClickFavourite = {
                                   viewModel.toggleFavourite(
                                        cat = cat
                                   )
                                },
                                isFavourite = viewModel.favouriteCats[cat.id] ?: false,
                                onClick = {
                                    navController.navigate("catBreedsDetails/${cat.id}")
                                }
                            )
                        }
                    }
                }
            }
        }
        Button(
            onClick = { navController.navigate("catBreedFavouriteScreen") },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .align(Alignment.BottomCenter)
        ) {
            Text(text = stringResource(id = R.string.go_to_favourites))
        }
    }
}
