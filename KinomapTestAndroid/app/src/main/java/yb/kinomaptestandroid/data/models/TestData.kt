package yb.kinomaptestandroid.data.models


import com.google.gson.annotations.SerializedName

data class TestData(
    @SerializedName("data")
    val data: List<Data>,
    @SerializedName("unlocked_content")
    val unlockedContent: List<Any>
)