package yb.kinomaptestandroid.ui.badges.categories.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import yb.kinomaptestandroid.ui.navigation.models.Badge

const val BADGE_NAME_MAX_LENGTH = 40

@Composable
fun BadgeItemView(
    modifier: Modifier = Modifier,
    badge: Badge,
    onClick: () -> Unit
) {

    val name = when {
        badge.name.length > BADGE_NAME_MAX_LENGTH -> {
            badge.name.substring(0, BADGE_NAME_MAX_LENGTH) + "..."
        }

        else -> badge.name
    }

    val badgeAlpha = when (badge.unlocked) {
        true -> 1f
        false -> 0.5f
    }

    Column(
        modifier = modifier
            .clickable(
                onClick = onClick
            )
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxSize()
                    .alpha(badgeAlpha),
                contentScale = ContentScale.Fit,
                model = when (badge.unlocked) {
                    true -> badge.unlockedImgUrl
                    false -> badge.lockedImgUrl
                },
                contentDescription = null
            )
            if (!badge.unlocked) {
                Image(
                    modifier = Modifier.size(48.dp),
                    imageVector = Icons.Default.Lock,
                    contentDescription = null
                )
            }
        }
        Spacer(
            modifier = Modifier.height(8.dp)
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = name,
            textAlign = TextAlign.Center,
            minLines = 2,
            maxLines = 2
        )

    }
}