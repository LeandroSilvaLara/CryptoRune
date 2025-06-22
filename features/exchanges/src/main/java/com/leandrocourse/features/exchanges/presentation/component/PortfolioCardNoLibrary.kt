package com.leandrocourse.features.exchanges.presentation.component

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.leandrocourse.libraries.design.extension.toPx
import com.leandrocourse.libraries.design.theme.PlutoTheme

@Composable
internal fun PortfolioCardNoLibrary(
    modifier: Modifier = Modifier
) {
    val rotationX = remember { Animatable(0f) }
    val rotationY = remember { Animatable(0f) }
    val chartData = remember {
        listOf(10f, 14f, 12f, 18f, 15f, 19f, 22f, 20f, 24f)
    }

    val cardColor = Color(0xFF5465FF)
    val contentColor = Color.White
    LaunchedEffect(Unit) {
        rotationX.animateTo(
            targetValue = 10f,
            animationSpec = tween(durationMillis = 1000)
        )
        rotationY.animateTo(
            targetValue = -5f,
            animationSpec = tween(durationMillis = 1000)
        )
        rotationX.animateTo(targetValue = 0f, animationSpec = tween(durationMillis = 500))
        rotationY.animateTo(targetValue = 0f, animationSpec = tween(durationMillis = 500))
    }
    Card(
        modifier = modifier
            .fillMaxWidth()
            .graphicsLayer(
                rotationX = rotationX.value,
                rotationY = rotationY.value,
                shadowElevation = 8.dp.toPx()
            )
            .clip(RoundedCornerShape(PlutoTheme.radius.large)),
        shape = RoundedCornerShape(PlutoTheme.radius.large),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Box(
            modifier = Modifier.background(cardColor)
        ) {
            SparkLineChart(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                data = chartData,
                graphColor = contentColor
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(PlutoTheme.dimen.dp24)
            ) {
                Text(
                    text = "Meu Portfólio",
                    style = PlutoTheme.typography.bodyLarge,
                    color = contentColor.copy(alpha = 0.8f),
                    fontWeight = FontWeight.Medium
                )
                Spacer(Modifier.height(PlutoTheme.dimen.dp8))
                Text(
                    text = "$189,543.00",
                    style = PlutoTheme.typography.headlineLarge,
                    color = contentColor,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.height(PlutoTheme.dimen.dp16))
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(50))
                        .background(Color(0xFF3DDC97).copy(alpha = 0.9f))
                        .padding(horizontal = PlutoTheme.dimen.dp12, vertical = PlutoTheme.dimen.dp6),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Variação positiva",
                        tint = contentColor,
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = "+21.54%",
                        style = PlutoTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold,
                        color = contentColor
                    )
                }
            }
        }
    }
}
@Preview
@Composable
private fun PortfolioCardNoLibraryPreview() {
    PlutoTheme {
        PortfolioCardNoLibrary()
    }
}