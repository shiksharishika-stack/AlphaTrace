package com.alphatrace.app.ui.tracing

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alphatrace.app.data.LetterPaths
import com.alphatrace.app.data.ProgressRepository
import com.alphatrace.app.ui.components.StarRating
import com.alphatrace.app.ui.theme.Coral
import com.alphatrace.app.ui.theme.SoftGreen
import com.alphatrace.app.ui.theme.WarmCream
import com.alphatrace.app.ui.theme.White
import kotlinx.coroutines.launch

@Composable
fun TracingScreen(
    letter: Char,
    progressRepository: ProgressRepository,
    onBack: () -> Unit,
    onNextLetter: () -> Unit
) {
    val letterDef = remember(letter) { LetterPaths.getLetter(letter) }
    val completedStrokes = remember { mutableStateListOf<List<Offset>>() }
    val currentStroke = remember { mutableStateListOf<Offset>() }
    var scored by remember { mutableStateOf(false) }
    var stars by remember { mutableIntStateOf(0) }
    var message by remember { mutableStateOf("Trace the letter $letter") }
    var canvasSize by remember { mutableFloatStateOf(0f) }
    val scope = rememberCoroutineScope()

    fun reset() {
        completedStrokes.clear()
        currentStroke.clear()
        scored = false
        stars = 0
        message = "Trace the letter $letter"
    }

    fun doScore() {
        if (scored) return
        val allStrokes = completedStrokes.toList()
        if (allStrokes.isEmpty()) return

        val result = HitDetection.score(allStrokes, letterDef, canvasSize)
        stars = result.stars
        scored = true
        message = when (result.stars) {
            3 -> "Amazing! Perfect trace!"
            2 -> "Great job! Keep practicing!"
            1 -> "Good start! Try again!"
            else -> "Keep trying! You can do it!"
        }

        if (result.stars > 0) {
            scope.launch {
                progressRepository.saveStars(letter, result.stars)
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(WarmCream)
            .padding(top = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Back button row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            OutlinedButton(
                onClick = onBack,
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Back")
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Letter heading
        Text(
            text = letter.toString(),
            style = MaterialTheme.typography.displayLarge,
            color = Coral
        )

        // Star display
        StarRating(stars = stars, size = 28.sp)

        Spacer(modifier = Modifier.height(8.dp))

        // Message
        Text(
            text = message,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Tracing canvas
        TracingCanvas(
            letter = letterDef,
            userStrokes = completedStrokes,
            currentStroke = currentStroke.toList(),
            onDragStart = { offset ->
                if (!scored) {
                    currentStroke.clear()
                    currentStroke.add(offset)
                }
            },
            onDrag = { offset ->
                if (!scored) {
                    currentStroke.add(offset)
                }
            },
            onDragEnd = {
                if (!scored && currentStroke.isNotEmpty()) {
                    completedStrokes.add(currentStroke.toList())
                    currentStroke.clear()
                }
            },
            modifier = Modifier.onSizeChanged { size ->
                canvasSize = size.width.toFloat()
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Action buttons
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            if (!scored) {
                Button(
                    onClick = { doScore() },
                    colors = ButtonDefaults.buttonColors(containerColor = Coral),
                    shape = RoundedCornerShape(12.dp),
                    enabled = completedStrokes.isNotEmpty()
                ) {
                    Text("Check", color = White)
                }
                Spacer(modifier = Modifier.width(12.dp))
                OutlinedButton(
                    onClick = { reset() },
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Clear")
                }
            } else {
                Button(
                    onClick = { reset() },
                    colors = ButtonDefaults.buttonColors(containerColor = Coral),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Try Again", color = White)
                }
                Spacer(modifier = Modifier.width(12.dp))
                Button(
                    onClick = onNextLetter,
                    colors = ButtonDefaults.buttonColors(containerColor = SoftGreen),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Next Letter", color = White)
                }
            }
        }
    }
}
