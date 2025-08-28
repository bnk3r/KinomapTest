package yb.kinomaptestandroid.ui.navigation.models

import kotlinx.serialization.Serializable

@Serializable
object BadgesListScreenNav

@Serializable
data class BadgesDetailsScreenNav(
    val badgeId: Int
)