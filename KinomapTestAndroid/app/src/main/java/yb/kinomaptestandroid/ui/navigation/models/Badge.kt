package yb.kinomaptestandroid.ui.navigation.models

import kotlinx.serialization.Serializable

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

    val unlocked: Boolean
        get() = unlockedDateEpochTime != null

}
