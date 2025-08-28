package yb.kinomaptestandroid.data.models


import com.google.gson.annotations.SerializedName

data class Badge(
    @SerializedName("action")
    val action: String,
    @SerializedName("category")
    val category: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("images_url")
    val imagesUrl: ImagesUrl,
    @SerializedName("name")
    val name: String,
    @SerializedName("unlocked_date")
    val unlockedDate: Int?,
    @SerializedName("unlocked_percent")
    val unlockedPercent: Int?,
)