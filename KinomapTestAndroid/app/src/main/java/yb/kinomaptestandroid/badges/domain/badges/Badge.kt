package yb.kinomaptestandroid.badges.domain.badges

import kotlinx.serialization.Serializable
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@Serializable
data class Badge(
    val id: Int,
    val name: String,
    val desc: String,
    val category: String,
    val unlockedDateEpochTime: Int?,
    val unlockedPercent: Int?,
    val unlockedImgUrl: String,
    val lockedImgUrl: String
) {

    val isUnlocked: Boolean
        get() = unlockedDateEpochTime != null

    val readableDate: String? = unlockedDateEpochTime?.toLong()?.let { time ->
        val formatter = DateTimeFormatter
            .ofPattern("dd/MM/uuuu HH:mm")
            .withZone(ZoneId.systemDefault())
        val instant = Instant.ofEpochSecond(time)
        formatter.format(instant)
    }

    val isComplete = unlockedPercent != null && unlockedPercent == 100

}
