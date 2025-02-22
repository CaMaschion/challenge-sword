package com.example.challenge_sword.ui.components

import android.widget.Toast
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.challenge_sword.R
import com.example.challenge_sword.data.model.CatResponse

@Composable
fun CatBreedDetailComponent(
    cat: CatResponse?,
    onBackButtonClick: () -> Unit
) {
    val isFavorite = remember { mutableStateOf(false) }
    val tint by animateColorAsState(
        targetValue = if (isFavorite.value) Color.Red else Color.Red,
        animationSpec = tween(durationMillis = 500)
    )

    val context = LocalContext.current

    if (cat == null) {
        CatBreedDetailNotFoundComponent(onBackButtonClick = onBackButtonClick)
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
                    val imageUrl = catDetails.url
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
                        text = catDetails.breeds.firstOrNull()?.name.orEmpty(),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                    )

                    Spacer(modifier = Modifier.size(8.dp))

                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append("Origin")
                            }
                            append(": ${catDetails.breeds.firstOrNull()?.origin.orEmpty()}")
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
                            append(": ${catDetails.breeds.firstOrNull()?.temperament.orEmpty()}")
                        },
                        style = MaterialTheme.typography.bodyMedium,
                    )

                    Spacer(modifier = Modifier.size(8.dp))

                    Text(
                        modifier = Modifier
                            .padding(8.dp),
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append("Life Span")
                            }
                            append(": ${catDetails.breeds.firstOrNull()?.description.orEmpty()}")
                        },
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }

                IconButton(
                    onClick = {
                        isFavorite.value = !isFavorite.value
                        val message = if (isFavorite.value) {
                            "Foi adicionado aos favoritos"
                        } else {
                            "Foi excluído dos favoritos"
                        }
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                    },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                ) {
                    val icon = if (isFavorite.value) {
                        painterResource(id = R.drawable.ic_favorite_filled)
                    } else {
                        painterResource(id = R.drawable.ic_favorite_border)
                    }
                    Icon(
                        modifier = Modifier.size(100.dp),
                        painter = icon,
                        contentDescription = "Favorite",
                        tint = tint,
                    )
                }
            }
        }
    }
}