package com.alphatrace.app.ui.tracing

import androidx.compose.ui.geometry.Offset
import com.alphatrace.app.data.LetterDefinition
import kotlin.math.sqrt

data class ScoreResult(
    val coverage: Float,
    val accuracy: Float,
    val combined: Float,
    val stars: Int
)

object HitDetection {

    private const val TOLERANCE_FRACTION = 0.07f
    private const val SAMPLE_SPACING = 0.02f

    fun score(
        userStrokes: List<List<Offset>>,
        letter: LetterDefinition,
        canvasSize: Float
    ): ScoreResult {
        val tolerance = canvasSize * TOLERANCE_FRACTION
        val toleranceSq = tolerance * tolerance

        val userPoints = userStrokes.flatten()
        if (userPoints.isEmpty()) return ScoreResult(0f, 0f, 0f, 0)

        // Sample guide points along each stroke segment
        val guidePoints = mutableListOf<Offset>()
        for (stroke in letter.strokes) {
            val pts = stroke.points.map { Offset(it.x * canvasSize, it.y * canvasSize) }
            for (i in 0 until pts.size - 1) {
                val from = pts[i]
                val to = pts[i + 1]
                val dist = distance(from, to)
                val steps = (dist / (canvasSize * SAMPLE_SPACING)).toInt().coerceAtLeast(1)
                for (s in 0..steps) {
                    val t = s.toFloat() / steps
                    guidePoints.add(Offset(
                        from.x + (to.x - from.x) * t,
                        from.y + (to.y - from.y) * t
                    ))
                }
            }
        }

        // Coverage: fraction of guide points that have a nearby user point
        var coveredCount = 0
        for (gp in guidePoints) {
            for (up in userPoints) {
                if (distanceSq(gp, up) <= toleranceSq) {
                    coveredCount++
                    break
                }
            }
        }
        val coverage = if (guidePoints.isNotEmpty()) coveredCount.toFloat() / guidePoints.size else 0f

        // Accuracy: fraction of user points near a guide line segment
        val guideSegments = buildGuideSegments(letter, canvasSize)
        var accurateCount = 0
        for (up in userPoints) {
            for ((segA, segB) in guideSegments) {
                if (pointToSegmentDistSq(up, segA, segB) <= toleranceSq) {
                    accurateCount++
                    break
                }
            }
        }
        val accuracy = if (userPoints.isNotEmpty()) accurateCount.toFloat() / userPoints.size else 0f

        val combined = coverage * 0.6f + accuracy * 0.4f

        val stars = when {
            combined >= 0.80f && coverage >= 0.70f -> 3
            combined >= 0.55f && coverage >= 0.50f -> 2
            combined >= 0.35f && coverage >= 0.30f -> 1
            else -> 0
        }

        return ScoreResult(coverage, accuracy, combined, stars)
    }

    private fun buildGuideSegments(letter: LetterDefinition, canvasSize: Float): List<Pair<Offset, Offset>> {
        val segments = mutableListOf<Pair<Offset, Offset>>()
        for (stroke in letter.strokes) {
            val pts = stroke.points.map { Offset(it.x * canvasSize, it.y * canvasSize) }
            for (i in 0 until pts.size - 1) {
                segments.add(pts[i] to pts[i + 1])
            }
        }
        return segments
    }

    private fun distance(a: Offset, b: Offset): Float {
        val dx = a.x - b.x
        val dy = a.y - b.y
        return sqrt(dx * dx + dy * dy)
    }

    private fun distanceSq(a: Offset, b: Offset): Float {
        val dx = a.x - b.x
        val dy = a.y - b.y
        return dx * dx + dy * dy
    }

    private fun pointToSegmentDistSq(p: Offset, a: Offset, b: Offset): Float {
        val dx = b.x - a.x
        val dy = b.y - a.y
        val lenSq = dx * dx + dy * dy
        if (lenSq == 0f) return distanceSq(p, a)

        val t = ((p.x - a.x) * dx + (p.y - a.y) * dy) / lenSq
        val clamped = t.coerceIn(0f, 1f)
        val proj = Offset(a.x + clamped * dx, a.y + clamped * dy)
        return distanceSq(p, proj)
    }
}
