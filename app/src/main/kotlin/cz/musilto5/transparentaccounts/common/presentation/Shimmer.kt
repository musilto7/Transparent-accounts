package cz.musilto5.transparentaccounts.common.presentation

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

fun Modifier.drawShimmer(
    baseColor: Color,
    highlightColor: Color,
    shimmerOffset: Float
): Modifier = this.then(
    Modifier.drawBehind {
        val brush = Brush.linearGradient(
            colors = listOf(
                baseColor,
                baseColor,
                highlightColor,
                baseColor,
                baseColor
            ),
            start = Offset(size.width * (shimmerOffset - 0.4f), 0f),
            end = Offset(size.width * (shimmerOffset + 0.4f), 0f)
        )
        drawRect(brush = brush)
    }
)
