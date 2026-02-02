package com.alphatrace.app.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alphatrace.app.data.ProgressRepository
import com.alphatrace.app.ui.components.LetterTile
import com.alphatrace.app.ui.theme.WarmCream

@Composable
fun HomeScreen(
    progressRepository: ProgressRepository,
    onLetterClick: (Char) -> Unit
) {
    val allStars by progressRepository.getAllStars().collectAsState(initial = emptyMap())
    val letters = ('A'..'Z').toList()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(WarmCream)
            .padding(top = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "AlphaTrace",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = "Tap a letter to start tracing!",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            contentPadding = PaddingValues(horizontal = 24.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(letters) { letter ->
                LetterTile(
                    letter = letter,
                    stars = allStars[letter] ?: 0,
                    onClick = { onLetterClick(letter) }
                )
            }
        }
    }
}
