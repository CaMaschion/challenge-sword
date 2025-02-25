import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.challenge_sword.R
import com.example.challenge_sword.ui.components.CatBreedDetailComponent
import com.example.challenge_sword.ui.components.CatBreedTopBarComponent
import com.example.challenge_sword.ui.presentation.detailscreen.CatBreedsDetailsViewModel

@Composable
fun CatBreedsDetailsScreen(catId: String, onBackButtonClick: () -> Unit) {
    val viewModel: CatBreedsDetailsViewModel = hiltViewModel()
    val isLoading by viewModel.isLoading.collectAsState()
    val cat by viewModel.selectedCatBreed
    val isFavourite by viewModel.isFavourite.collectAsState()

    LaunchedEffect(catId) {
        viewModel.getSelectedCatBreed(catId)
        viewModel.fetchFavouriteCats()
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White)
    ) {
        CatBreedTopBarComponent(
            title = stringResource(id = R.string.cat_breeds_details),
            showBackButton = true,
            onBackButtonClick = onBackButtonClick
        )

        if (isLoading) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            CatBreedDetailComponent(
                cat = cat,
                onBackButtonClick = onBackButtonClick,
                isFavourite = isFavourite,
                onClickFavourite = {
                    cat?.let {
                        viewModel.toggleFavourite(
                            cat = it
                        )
                    }
                }
            )
        }
    }
}
