package com.alphatrace.app.ui.tracing

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.alphatrace.app.data.LetterDefinition
import com.alphatrace.app.ui.theme.Coral
import com.alphatrace.app.ui.theme.GuideGray
import com.alphatrace.app.ui.theme.White

@Composable
fun TracingCanvas(
    letter: LetterDefinition,
    userStrokes: List<List<Offset>>,
    currentStroke: List<Offset>,
    onDragStart: (Offset) -> Unit,
    onDrag: (Offset) -> Unit,
    onDragEnd: () -> Unit,
    modifier: Modifier = Modifier
) {
    Canvas(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .aspectRatio(1f)
            .shadow(8.dp, RoundedCornerShape(20.dp))
            .clip(RoundedCornerShape(20.dp))
            .background(White)
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragStart = { offset -> onDragStart(offset) },
                    onDrag = { change, _ ->
                        change.consume()
                        onDrag(change.position)
                    },
                    onDragEnd = { onDragEnd() },
                    onDragCancel = { onDragEnd() }
                )
            }
    ) {
        val canvasSize = size.minDimension

        // Draw guide paths (dashed)
        val dashEffect = PathEffect.dashPathEffect(floatArrayOf(15f, 15f))
        for (stroke in letter.strokes) {
            val scaled = stroke.points.map { Offset(it.x * canvasSize, it.y * canvasSize) }
            for (i in 0 until scaled.size - 1) {
                drawLine(
                    color = GuideGray,
                    start = scaled[i],
                    end = scaled[i + 1],
                    strokeWidth = 14f,
                    cap = StrokeCap.Round,
                    pathEffect = dashEffect
                )
            }
        }

        // Draw completed user strokes
        val strokeStyle = Stroke(
            width = 12f,
            cap = StrokeCap.Round,
            join = StrokeJoin.Round
        )
        for (userStroke in userStrokes) {
            drawUserStroke(userStroke, Coral, strokeStyle)
        }

        // Draw current active stroke
        if (currentStroke.isNotEmpty()) {
            drawUserStroke(currentStroke, Coral, strokeStyle)
        }
    }
}

private fun androidx.compose.ui.graphics.drawscope.DrawScope.drawUserStroke(
    points: List<Offset>,
    color: Color,
    stroke: Stroke
) {
    for (i in 0 until points.size - 1) {
        drawLine(
            color = color,
            start = points[i],
            end = points[i + 1],
            strokeWidth = stroke.width,
            cap = StrokeCap.Round
        )
    }
}
