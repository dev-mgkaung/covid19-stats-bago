package gov.mm.covid19statsbago.datas

import com.google.gson.annotations.SerializedName

/**
 * Created by Mg Kaung on 4/4/2020.
 */
data class TreatmentDataResponse (
    @SerializedName("quarantine_stats")
    val datas: List<QurantineData>
)

data class QurantineData(
    @SerializedName("id")
    var id: String = "",
    @SerializedName("date")
    var date: String = "",
    @SerializedName("district")
    var district: String = "",
    @SerializedName("township")
    var township: String = "",
    @SerializedName("quarantine")
    var quarantine: Quarantine ?=null
)

data class Quarantine(

    @SerializedName("returned_count")
    var returned_count: String = "",
    @SerializedName("keep_count")
    var keep_count: KeepCount ?= null,
    @SerializedName("suspicion_count")
    var suspicion_count: SuspicionCount ?= null,
    @SerializedName("positive_count")
    var positive_count: PositiveCount ?= null,
    @SerializedName("total_release_count")
    var total_release_count: String = ""
)

data class  KeepCount(

    @SerializedName("home")
    var home: String = "",

    @SerializedName("religious_building")
    var religious_building: String = "",

    @SerializedName("hospital")
    var hospital: String = "",
    @SerializedName("hotel")
    var hotel: String = "",

    @SerializedName("avenue")
    var avenue: String = "",

    @SerializedName("schools")
    var schools: String = "",

    @SerializedName("others")
    var others: String = "",

    @SerializedName("total")
    var total: String = "",

    @SerializedName("release_count")
    var release_count: String = ""
)

data class SuspicionCount(

    @SerializedName("home")
    var home: String = "",

    @SerializedName("religious_building")
    var religious_building: String = "",

    @SerializedName("hospital")
    var hospital: String = "",

    @SerializedName("hotel")
    var hotel: String = "",

    @SerializedName("avenue")
    var avenue: String = "",

    @SerializedName("schools")
    var schools: String = "",

    @SerializedName("others")
    var others: String = "",

    @SerializedName("total")
    var total: String = "",

    @SerializedName("release_count")
    var release_count: String = ""
)

data class PositiveCount(
    @SerializedName("hospital")
    var hospital: String = "",

    @SerializedName("death")
    var death: String = "",

    @SerializedName("release_count")
    var release_count: String = ""

)