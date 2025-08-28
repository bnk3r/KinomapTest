package yb.kinomaptestandroid.ui.badges.details.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import yb.kinomaptestandroid.ui.navigation.models.Badge
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
@Composable
fun BadgesDetailsScreen(
    modifier: Modifier = Modifier,
    badge: Badge
) {
    val date: String? = badge.unlockedDateEpochTime?.toLong()?.let { time ->
        val formatter = DateTimeFormatter
            .ofPattern("dd/MM/uuuu HH:mm")
            .withZone(ZoneId.systemDefault())
        val instant = Instant.ofEpochSecond(time)
        formatter.format(instant)
    }

    Column(
        modifier = modifier.padding(16.dp)
    ) {
        BadgeImageView(
            modifier = Modifier.fillMaxWidth(),
            badgeUrl = when (badge.unlocked) {
                true -> badge.unlockedImgUrl
                false -> badge.lockedImgUrl
            },
            unlockedDate = date
        )
        Spacer(
            modifier = Modifier.height(16.dp)
        )
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
        BadgeProgressionView(
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