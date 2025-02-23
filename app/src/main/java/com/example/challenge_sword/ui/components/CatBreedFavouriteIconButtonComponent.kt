package com.example.challenge_sword.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.challenge_sword.R

@Composable
fun CatBreedFavouriteIconButtonComponent(
    isFavourite: Boolean,
    onClick: () -> Unit = {},
) {
    val tint by animateColorAsState(
        targetValue = if (isFavourite) Color.Red else Color.Red,
        animationSpec = tween(durationMillis = 500)
    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        IconButton(
            onClick = onClick,
            modifier = Modifier.align(Alignment.TopEnd)
        ) {
            val icon = if (isFavourite) {
                painterResource(id = R.drawable.ic_favorite_filled)
            } else {
                painterResource(id = R.drawable.ic_favorite_border)
            }
            Icon(
                modifier = Modifier.size(100.dp),
                painter = icon,
                contentDescription = "Favourite",
                tint = tint,
            )
        }
    }
}
