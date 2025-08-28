package yb.kinomaptestandroid.ui.badges.details.views

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BadgeProgressionView(
    modifier: Modifier = Modifier,
    progression: Int
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        LinearProgressIndicator(
            modifier = Modifier.weight(1f),
            progress = { progression / 100f }
        )
        Spacer(
            modifier = Modifier.width(16.dp)
        )
        Text("$progression%")
    }
}