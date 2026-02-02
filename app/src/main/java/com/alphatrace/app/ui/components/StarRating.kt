package com.alphatrace.app.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun StarRating(
    stars: Int,
    maxStars: Int = 3,
    size: TextUnit = 16.sp,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        repeat(maxStars) { index ->
            Text(
                text = if (index < stars) "\u2B50" else "\u2606",
                fontSize = size
            )
        }
    }
}
