package com.alphatrace.app.data

import androidx.compose.ui.geometry.Offset

data class Stroke(val points: List<Offset>)

data class LetterDefinition(
    val char: Char,
    val strokes: List<Stroke>
)
