package yb.kinomaptestandroid.ui.effects.views

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import yb.kinomaptestandroid.ui.effects.models.Particle
import kotlin.random.Random

@Composable
fun CanvasExplosion(
    progress: Float,
    particlesCount: Int = 100,
    size: Dp,
) {
    val density = LocalDensity.current
    val sizePx = with(density) { size.toPx() }
    val sizeHalfPx = sizePx / 2

    val particles = remember {
        List(particlesCount) {
            Particle(
                density = density,
                color = Color(
                    Random.nextInt(256),
                    Random.nextInt(256),
                    Random.nextInt(256),
                    alpha = 255
                ),
                startXPos = sizeHalfPx.toInt(),
                startYPos = sizeHalfPx.toInt(),
                maxHorizontalDisplacement = sizeHalfPx * ((-90..90).random() / 100f),
                maxVerticalDisplacement = sizeHalfPx * ((20..38).random() / 100f)
            )
        }
    }

    particles.forEach { particle ->
        particle.updateProgress(progress)
    }

    Canvas(
        modifier = Modifier
            .size(size)
            .background(Color.Transparent)
    ) {
        particles.forEach { particle ->
            drawCircle(
                color = particle.color,
                alpha = particle.alpha,
                radius = particle.currentRadius,
                center = Offset(
                    particle.currentXPos,
                    particle.currentYPos
                )
            )
        }
    }
}

