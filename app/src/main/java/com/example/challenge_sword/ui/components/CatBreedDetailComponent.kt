package com.example.challenge_sword.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.challenge_sword.R
import com.example.challenge_sword.domain.model.CatBreed

@Composable
fun CatBreedDetailComponent(
    cat: CatBreed?,
    onBackButtonClick: () -> Unit,
    onClickFavourite: () -> Unit = {},
) {

    if (cat == null) {
        CatBreedNotFoundComponent(
            message = stringResource(R.string.cat_not_found),
            onBackButtonClick = onBackButtonClick
        )
    } else {
        cat.let { catDetails ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .background(color = Color.White)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .background(color = Color.White),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val imageUrl = catDetails.imageUrl
                    Image(
                        painter = rememberAsyncImagePainter(model = imageUrl),
                        contentScale = ContentScale.Crop,
                        contentDescription = "Cat Image",
                        modifier = Modifier
                            .size(150.dp)
                            .clip(CircleShape)
                            .align(Alignment.CenterHorizontally),
                    )

                    Spacer(modifier = Modifier.size(16.dp))

                    Text(
                        text = catDetails.name,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                    )

                    Spacer(modifier = Modifier.size(8.dp))

                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append("Origin")
                            }
                            append(": ${catDetails.origin}")
                        },
                        style = MaterialTheme.typography.bodyMedium,
                    )

                    Spacer(modifier = Modifier.size(8.dp))

                    Text(
                        modifier = Modifier
                            .padding(8.dp),
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append("Temperament")
                            }
                            append(": ${catDetails.temperament}")
                        },
                        style = MaterialTheme.typography.bodyMedium,
                    )

                    Spacer(modifier = Modifier.size(8.dp))

                    Text(
                        modifier = Modifier
                            .padding(8.dp),
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append("Description")
                            }
                            append(": ${catDetails.description}")
                        },
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }

                CatBreedFavouriteIconButtonComponent(
                    isFavourite = cat.isFavourite,
                    onClick = onClickFavourite
                )
            }
        }
    }
}
