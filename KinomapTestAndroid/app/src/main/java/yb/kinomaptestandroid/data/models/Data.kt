package yb.kinomaptestandroid.data.models


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("badges")
    val badges: List<Badge>,
    @SerializedName("name")
    val name: String
)