package yb.kinomaptestandroid.badges.presentation.details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BadgeProgression(
    modifier: Modifier = Modifier,
    progression: Int
) {
    val brushFull = Brush.horizontalGradient(
        listOf(
            Color(0xfffded9d),
            Color(0xffc45dad),
            Color(0xff5ee7ea),
            Color(0xfffcc700)
        )
    )
    val brushHigh = Brush.horizontalGradient(
        listOf(Color(0xfffcc700), Color(0xfffcc700))
    )
    val colorHigh = Brush.horizontalGradient(
        listOf(Color(0xff5ee7ea), Color(0xff5ee7ea))
    )

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = modifier
                .clip(RoundedCornerShape(16.dp))
                .background(Color.Gray)
                .height(10.dp)
                .weight(1f)
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .background(
                        when (progression) {
                            100 -> brushFull
                            in 50 until 100 -> colorHigh
                            else -> brushHigh
                        }
                    )
                    .fillMaxHeight()
                    .fillMaxWidth(progression / 100f)
            )
        }

        Spacer(
            modifier = Modifier.width(16.dp)
        )
        Text("$progression%")
    }
}