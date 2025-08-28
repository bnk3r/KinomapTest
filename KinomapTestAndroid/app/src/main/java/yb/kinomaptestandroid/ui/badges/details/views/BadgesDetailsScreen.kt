package yb.kinomaptestandroid.ui.badges.details.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
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
    val date = badge.unlockedDateEpochTime?.toLong()?.let { time ->
        val formatter = DateTimeFormatter
            .ofPattern("dd/MM/uuuu HH:mm")
            .withZone(ZoneId.systemDefault())
        val instant = Instant.ofEpochSecond(time)
        formatter.format(instant)
    } ?: "null"

    Column(
        modifier = modifier
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentScale = ContentScale.Fit,
            model = when (badge.unlocked) {
                true -> badge.unlockedImgUrl
                false -> badge.lockedImgUrl
            },
            contentDescription = null
        )
        Text("name=${badge.name}")
        Text("category=${badge.category}")
        Text("progression=${badge.unlockedPercent ?: 0}%")
        Text("unlocked_date=$date")
        Text("description=${badge.desc}")
    }
}