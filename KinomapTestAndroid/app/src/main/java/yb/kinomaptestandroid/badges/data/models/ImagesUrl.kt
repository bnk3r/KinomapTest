package yb.kinomaptestandroid.badges.data.models


import com.google.gson.annotations.SerializedName

data class ImagesUrl(
    @SerializedName("locked")
    val locked: String,
    @SerializedName("unlocked")
    val unlocked: String
)