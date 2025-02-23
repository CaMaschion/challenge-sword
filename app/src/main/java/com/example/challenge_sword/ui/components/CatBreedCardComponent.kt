package com.example.challenge_sword.ui.components

import android.content.Context
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.challenge_sword.domain.model.CatBreed

@Composable
fun CatBreedCardComponent(
    cat: CatBreed,
    onClick: () -> Unit,
    context: Context,
    showLifeSpan: Boolean = false
) {
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
            CatBreedFavouriteIconButtonComponent(
                isFavourite = remember { mutableStateOf(false) },
                context = context
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val imageUrl = cat.imageUrl
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

            val breedName = cat.name
            Text(
                text = breedName,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
            )

            if (showLifeSpan) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Lifespan: ${cat.getAverageLifeSpan()}",
                    style = MaterialTheme.typography.bodySmall,
                )
            }
        }
    }
}
