package yb.kinomaptestandroid.badges.presentation.details.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import yb.kinomaptestandroid.badges.domain.badges.Badge

@Composable
fun BadgeDetailsLandscape(
    modifier: Modifier = Modifier,
    badge: Badge,
    unlockedDate: String?,
    badgeOwned: Boolean
) {
    Row(
        modifier = modifier.padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            BadgeImage(
                modifier = Modifier
                    .fillMaxHeight()
                    .aspectRatio(1f),
                badgeUrl = when (badge.isUnlocked) {
                    true -> badge.unlockedImgUrl
                    false -> badge.lockedImgUrl
                },
                unlockedDate = unlockedDate,
                badgeOwned = badgeOwned
            )
        }
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp)
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = badge.name,
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "(${badge.category})",
                style = MaterialTheme.typography.labelLarge,
                textAlign = TextAlign.Center
            )
            Spacer(
                modifier = Modifier.height(16.dp)
            )
            BadgeProgression(
                modifier = Modifier.fillMaxWidth(),
                progression = badge.unlockedPercent ?: 0
            )
            Spacer(
                modifier = Modifier.height(16.dp)
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = badge.desc,
                textAlign = TextAlign.Center
            )
        }
    }
}