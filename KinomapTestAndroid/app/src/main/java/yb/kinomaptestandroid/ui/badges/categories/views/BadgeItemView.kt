package yb.kinomaptestandroid.ui.badges.categories.views

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import yb.kinomaptestandroid.ui.navigation.models.Badge

@Composable
fun BadgeItemView(
    modifier: Modifier = Modifier,
    badge: Badge
) {
    Column(
        modifier = modifier
            .border(1.dp, Color.Black)
            .padding(8.dp)
    ) {
        Text(badge.name)
        AsyncImage(
            modifier = Modifier.size(100.dp),
            contentScale = ContentScale.Fit,
            model = when (badge.unlocked) {
                true -> badge.unlockedImgUrl
                false -> badge.lockedImgUrl
            },
            contentDescription = null
        )
    }
}