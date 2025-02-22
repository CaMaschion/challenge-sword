package com.example.challenge_sword.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.challenge_sword.R
import com.example.challenge_sword.data.model.CatResponse

@Composable
fun CatBreedComponent(cat: CatResponse, onClick: () -> Unit) {

    val isFavorite = remember { mutableStateOf(false) }

    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .clickable(onClick = onClick)
            .width(200.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
        ) {
            IconButton(
                onClick = {
                    isFavorite.value = !isFavorite.value
                }
            ) {
                val icon = if (isFavorite.value) {
                    painterResource(id = R.drawable.ic_favorite_filled)
                } else {
                    painterResource(id = R.drawable.ic_favorite_border)
                }
                Icon(
                    painter = icon,
                    contentDescription = "Favorite Breed",
                    modifier = Modifier
                        .size(30.dp),
                    tint = Color.Black
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val imageUrl = cat.url
            Image(
                painter = rememberAsyncImagePainter(model = imageUrl),
                contentScale = ContentScale.Crop,
                contentDescription = "Cat Image",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .align(Alignment.CenterHorizontally),
            )

            Spacer(modifier = Modifier.height(8.dp))

            val breedName = cat.breeds.first().name
            Text(
                text = breedName,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
            )

        }
    }
}
