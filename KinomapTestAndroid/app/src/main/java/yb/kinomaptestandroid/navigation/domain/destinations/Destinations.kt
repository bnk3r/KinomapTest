package yb.kinomaptestandroid.navigation.domain.destinations

import kotlinx.serialization.Serializable

@Serializable
object BadgesListScreenNav

@Serializable
data class BadgesDetailsScreenNav(
    val badgeId: Int
)