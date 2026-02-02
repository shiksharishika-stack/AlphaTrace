package com.alphatrace.app.data

import androidx.compose.ui.geometry.Offset

/**
 * All 26 uppercase letter definitions as normalized polyline strokes (0..1 coordinate space).
 * Curves are approximated with line segments for simple hit detection.
 */
object LetterPaths {

    private fun s(vararg pairs: Float): Stroke {
        val points = mutableListOf<Offset>()
        for (i in pairs.indices step 2) {
            points.add(Offset(pairs[i], pairs[i + 1]))
        }
        return Stroke(points)
    }

    val letters: Map<Char, LetterDefinition> = mapOf(
        'A' to LetterDefinition('A', listOf(
            s(0.5f, 0.1f, 0.15f, 0.9f),
            s(0.5f, 0.1f, 0.85f, 0.9f),
            s(0.27f, 0.6f, 0.73f, 0.6f)
        )),
        'B' to LetterDefinition('B', listOf(
            s(0.2f, 0.1f, 0.2f, 0.9f),
            s(0.2f, 0.1f, 0.6f, 0.1f, 0.75f, 0.18f, 0.78f, 0.3f, 0.75f, 0.42f, 0.6f, 0.5f, 0.2f, 0.5f),
            s(0.2f, 0.5f, 0.65f, 0.5f, 0.8f, 0.58f, 0.82f, 0.7f, 0.8f, 0.82f, 0.65f, 0.9f, 0.2f, 0.9f)
        )),
        'C' to LetterDefinition('C', listOf(
            s(0.8f, 0.25f, 0.7f, 0.15f, 0.55f, 0.1f, 0.4f, 0.1f, 0.25f, 0.15f, 0.15f, 0.25f,
              0.1f, 0.4f, 0.1f, 0.6f, 0.15f, 0.75f, 0.25f, 0.85f, 0.4f, 0.9f, 0.55f, 0.9f,
              0.7f, 0.85f, 0.8f, 0.75f)
        )),
        'D' to LetterDefinition('D', listOf(
            s(0.2f, 0.1f, 0.2f, 0.9f),
            s(0.2f, 0.1f, 0.5f, 0.1f, 0.65f, 0.15f, 0.75f, 0.25f, 0.8f, 0.4f, 0.8f, 0.6f,
              0.75f, 0.75f, 0.65f, 0.85f, 0.5f, 0.9f, 0.2f, 0.9f)
        )),
        'E' to LetterDefinition('E', listOf(
            s(0.75f, 0.1f, 0.2f, 0.1f),
            s(0.2f, 0.1f, 0.2f, 0.9f),
            s(0.2f, 0.9f, 0.75f, 0.9f),
            s(0.2f, 0.5f, 0.6f, 0.5f)
        )),
        'F' to LetterDefinition('F', listOf(
            s(0.75f, 0.1f, 0.2f, 0.1f),
            s(0.2f, 0.1f, 0.2f, 0.9f),
            s(0.2f, 0.5f, 0.6f, 0.5f)
        )),
        'G' to LetterDefinition('G', listOf(
            s(0.8f, 0.25f, 0.7f, 0.15f, 0.55f, 0.1f, 0.4f, 0.1f, 0.25f, 0.15f, 0.15f, 0.25f,
              0.1f, 0.4f, 0.1f, 0.6f, 0.15f, 0.75f, 0.25f, 0.85f, 0.4f, 0.9f, 0.55f, 0.9f,
              0.7f, 0.85f, 0.8f, 0.75f, 0.8f, 0.55f),
            s(0.55f, 0.55f, 0.8f, 0.55f)
        )),
        'H' to LetterDefinition('H', listOf(
            s(0.2f, 0.1f, 0.2f, 0.9f),
            s(0.8f, 0.1f, 0.8f, 0.9f),
            s(0.2f, 0.5f, 0.8f, 0.5f)
        )),
        'I' to LetterDefinition('I', listOf(
            s(0.35f, 0.1f, 0.65f, 0.1f),
            s(0.5f, 0.1f, 0.5f, 0.9f),
            s(0.35f, 0.9f, 0.65f, 0.9f)
        )),
        'J' to LetterDefinition('J', listOf(
            s(0.35f, 0.1f, 0.7f, 0.1f),
            s(0.55f, 0.1f, 0.55f, 0.7f, 0.5f, 0.8f, 0.4f, 0.88f, 0.3f, 0.9f, 0.2f, 0.85f, 0.15f, 0.75f)
        )),
        'K' to LetterDefinition('K', listOf(
            s(0.2f, 0.1f, 0.2f, 0.9f),
            s(0.75f, 0.1f, 0.2f, 0.5f),
            s(0.2f, 0.5f, 0.75f, 0.9f)
        )),
        'L' to LetterDefinition('L', listOf(
            s(0.2f, 0.1f, 0.2f, 0.9f),
            s(0.2f, 0.9f, 0.75f, 0.9f)
        )),
        'M' to LetterDefinition('M', listOf(
            s(0.1f, 0.9f, 0.1f, 0.1f),
            s(0.1f, 0.1f, 0.5f, 0.55f),
            s(0.5f, 0.55f, 0.9f, 0.1f),
            s(0.9f, 0.1f, 0.9f, 0.9f)
        )),
        'N' to LetterDefinition('N', listOf(
            s(0.15f, 0.9f, 0.15f, 0.1f),
            s(0.15f, 0.1f, 0.85f, 0.9f),
            s(0.85f, 0.9f, 0.85f, 0.1f)
        )),
        'O' to LetterDefinition('O', listOf(
            s(0.5f, 0.1f, 0.35f, 0.12f, 0.22f, 0.2f, 0.13f, 0.32f, 0.1f, 0.5f,
              0.13f, 0.68f, 0.22f, 0.8f, 0.35f, 0.88f, 0.5f, 0.9f, 0.65f, 0.88f,
              0.78f, 0.8f, 0.87f, 0.68f, 0.9f, 0.5f, 0.87f, 0.32f, 0.78f, 0.2f,
              0.65f, 0.12f, 0.5f, 0.1f)
        )),
        'P' to LetterDefinition('P', listOf(
            s(0.2f, 0.1f, 0.2f, 0.9f),
            s(0.2f, 0.1f, 0.6f, 0.1f, 0.75f, 0.18f, 0.8f, 0.3f, 0.75f, 0.42f, 0.6f, 0.5f, 0.2f, 0.5f)
        )),
        'Q' to LetterDefinition('Q', listOf(
            s(0.5f, 0.1f, 0.35f, 0.12f, 0.22f, 0.2f, 0.13f, 0.32f, 0.1f, 0.5f,
              0.13f, 0.68f, 0.22f, 0.8f, 0.35f, 0.88f, 0.5f, 0.9f, 0.65f, 0.88f,
              0.78f, 0.8f, 0.87f, 0.68f, 0.9f, 0.5f, 0.87f, 0.32f, 0.78f, 0.2f,
              0.65f, 0.12f, 0.5f, 0.1f),
            s(0.65f, 0.72f, 0.85f, 0.95f)
        )),
        'R' to LetterDefinition('R', listOf(
            s(0.2f, 0.1f, 0.2f, 0.9f),
            s(0.2f, 0.1f, 0.6f, 0.1f, 0.75f, 0.18f, 0.78f, 0.3f, 0.75f, 0.42f, 0.6f, 0.5f, 0.2f, 0.5f),
            s(0.5f, 0.5f, 0.8f, 0.9f)
        )),
        'S' to LetterDefinition('S', listOf(
            s(0.75f, 0.2f, 0.65f, 0.13f, 0.5f, 0.1f, 0.35f, 0.13f, 0.22f, 0.2f,
              0.2f, 0.3f, 0.25f, 0.4f, 0.35f, 0.47f, 0.5f, 0.5f, 0.65f, 0.53f,
              0.75f, 0.6f, 0.8f, 0.7f, 0.78f, 0.8f, 0.65f, 0.87f, 0.5f, 0.9f,
              0.35f, 0.87f, 0.25f, 0.8f)
        )),
        'T' to LetterDefinition('T', listOf(
            s(0.1f, 0.1f, 0.9f, 0.1f),
            s(0.5f, 0.1f, 0.5f, 0.9f)
        )),
        'U' to LetterDefinition('U', listOf(
            s(0.15f, 0.1f, 0.15f, 0.65f, 0.2f, 0.75f, 0.3f, 0.85f, 0.42f, 0.9f,
              0.58f, 0.9f, 0.7f, 0.85f, 0.8f, 0.75f, 0.85f, 0.65f, 0.85f, 0.1f)
        )),
        'V' to LetterDefinition('V', listOf(
            s(0.1f, 0.1f, 0.5f, 0.9f),
            s(0.5f, 0.9f, 0.9f, 0.1f)
        )),
        'W' to LetterDefinition('W', listOf(
            s(0.05f, 0.1f, 0.25f, 0.9f),
            s(0.25f, 0.9f, 0.5f, 0.4f),
            s(0.5f, 0.4f, 0.75f, 0.9f),
            s(0.75f, 0.9f, 0.95f, 0.1f)
        )),
        'X' to LetterDefinition('X', listOf(
            s(0.15f, 0.1f, 0.85f, 0.9f),
            s(0.85f, 0.1f, 0.15f, 0.9f)
        )),
        'Y' to LetterDefinition('Y', listOf(
            s(0.1f, 0.1f, 0.5f, 0.5f),
            s(0.9f, 0.1f, 0.5f, 0.5f),
            s(0.5f, 0.5f, 0.5f, 0.9f)
        )),
        'Z' to LetterDefinition('Z', listOf(
            s(0.15f, 0.1f, 0.85f, 0.1f),
            s(0.85f, 0.1f, 0.15f, 0.9f),
            s(0.15f, 0.9f, 0.85f, 0.9f)
        ))
    )

    fun getLetter(char: Char): LetterDefinition =
        letters[char.uppercaseChar()] ?: error("No definition for letter $char")
}
