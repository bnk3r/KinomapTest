package yb.kinomaptestandroid.ui.badges.details.views

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import yb.kinomaptestandroid.ui.navigation.models.Badge
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
@Composable
fun BadgeDetailsScreen(
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
    val badgeOwned = badge.unlockedPercent != null && badge.unlockedPercent == 100

    val configuration = LocalConfiguration.current
    val orientation = configuration.orientation

    when (orientation) {
        Configuration.ORIENTATION_PORTRAIT -> {
            BadgeDetailsPortrait(
                modifier = modifier,
                badge = badge,
                unlockedDate = date,
                badgeOwned = badgeOwned
            )
        }

        else -> {
            BadgeDetailsLandscape(
                modifier = modifier,
                badge = badge,
                unlockedDate = date,
                badgeOwned = badgeOwned
            )
        }
    }


}