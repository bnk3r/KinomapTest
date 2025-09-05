package yb.kinomaptestandroid.badges.presentation.details.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import yb.kinomaptestandroid.ui.presentation.animations.explosion.components.CanvasExplosion

@Composable
fun BadgeImage(
    modifier: Modifier = Modifier,
    badgeUrl: String,
    unlockedDate: String?,
    badgeOwned: Boolean
) {
    val progress = remember { Animatable(0f) }

    val brushBadge = when (badgeOwned) {
        true -> Brush.horizontalGradient(
            listOf(
                Color(0xfffded9d),
                Color(0xffc45dad),
                Color(0xff5ee7ea),
                Color(0xfffcc700)
            )
        )

        false -> Brush.horizontalGradient(
            listOf(Color.Transparent, Color.Transparent)
        )
    }

    LaunchedEffect(badgeOwned) {
        if (badgeOwned) {
            progress.animateTo(
                targetValue = 1f,
                animationSpec = tween(
                    durationMillis = 3000,
                    easing = LinearOutSlowInEasing
                )
            )
        }
    }

    Card(
        modifier = modifier
            .aspectRatio(1f)
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxSize()
                        .border(
                            BorderStroke(2.dp, brush = brushBadge),
                            RoundedCornerShape(16.dp)
                        )
                        .padding(2.dp)
                        .padding(16.dp),
                    contentScale = ContentScale.Fit,
                    model = badgeUrl,
                    contentDescription = null
                )
                if (badgeOwned) {
                    CanvasExplosion(
                        progress = progress.value,
                        particlesCount = 150,
                        size = 300.dp
                    )
                    // Temporary fix. The progress state animation does not trigger without
                    // calling the instance somewhere. (Need further investigation).
                    Text("value=${progress.value}", color = Color.Transparent)
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                verticalAlignment = Alignment.Bottom
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.End
                ) {
                    when (unlockedDate) {
                        null -> {
                            Image(
                                modifier = Modifier.size(48.dp),
                                imageVector = Icons.Default.Lock,
                                contentDescription = null
                            )
                        }

                        else -> {
                            Text(
                                text = unlockedDate,
                                style = MaterialTheme.typography.labelSmall
                            )
                        }
                    }
                }
            }
        }
    }
}