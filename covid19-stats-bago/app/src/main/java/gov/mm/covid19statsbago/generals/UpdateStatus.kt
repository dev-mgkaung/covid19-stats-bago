package gov.mm.covid19statsbago.generals

import com.google.gson.annotations.SerializedName

data class UpdateStatus(
    @SerializedName("apk_name")
    val apkName: String,
    @SerializedName("version")
    val version: String,
    @SerializedName("download_link")
    val downloadURL: String
)