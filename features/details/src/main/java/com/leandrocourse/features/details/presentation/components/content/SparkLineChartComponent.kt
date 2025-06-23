package com.leandrocourse.features.details.presentation.components.content

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

/**
 * Um Composable que desenha um gráfico de linha simples (sparkline) sem usar bibliotecas.
 * @param modifier O modificador a ser aplicado ao Canvas.
 * @param data Uma lista de números (Float) a serem plotados no gráfico.
 * @param graphColor A cor principal da linha e do preenchimento do gráfico.
 */
@Composable
fun SparkLineChart(
    modifier: Modifier = Modifier,
    data: List<Float>,
    graphColor: Color
) {
    if (data.isEmpty()) return // Não desenha nada se não houver dados

    Canvas(modifier = modifier) {
        val (canvasWidth, canvasHeight) = size

        val minY = data.minOrNull() ?: 0f
        val maxY = data.maxOrNull() ?: 0f
        val yRange = if (maxY > minY) maxY - minY else 1f
        val xStep = canvasWidth / (data.size - 1).toFloat()

        val linePath = Path()
        data.forEachIndexed { index, yValue ->
            val x = index * xStep
            val y = canvasHeight - ((yValue - minY) / yRange * canvasHeight)
            if (index == 0) {
                linePath.moveTo(x, y)
            } else {
                linePath.lineTo(x, y)
            }
        }

        val fillPath = Path().apply {
            addPath(linePath)
            lineTo(canvasWidth, canvasHeight)
            lineTo(0f, canvasHeight)
            close()
        }

        drawPath(
            path = fillPath,
            brush = Brush.verticalGradient(
                colors = listOf(
                    graphColor.copy(alpha = 0.3f),
                    Color.Transparent
                )
            )
        )

        drawPath(
            path = linePath,
            color = graphColor,
            style = Stroke(width = 2.5.dp.toPx())
        )
    }
}