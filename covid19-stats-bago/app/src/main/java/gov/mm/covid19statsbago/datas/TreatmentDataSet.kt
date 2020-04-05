package gov.mm.covid19statsbago.datas

import com.google.gson.annotations.SerializedName

/**
 * Created by Mg Kaung on 4/4/2020.
 */
data class TreatmentDataResponse(
    @SerializedName("rows")
    val data: List<QurantineData>
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
    @SerializedName("qrcount")
    var q_r_count: String = "",
    @SerializedName("qhquarantine")
    var q_h_quarantine: String = "",
    @SerializedName("qreligiousbuilding")
    var q_religious_building: String = "",
    @SerializedName("quarantineavenue")
    var quarantine_avenue: String = "",
    @SerializedName("quarantinehotel")
    var quarantine_hotel: String = "",
    @SerializedName("quarantineschools")
    var quarantine_schools: String = "",
    @SerializedName("quarantineothers")
    var quarantine_others: String = "",
    @SerializedName("quarantinetotal")
    var quarantine_total: String = "",
    @SerializedName("qreleasecount")
    var q_release_count: String = "",
    @SerializedName("scounthospital")
    var s_count_hospital: String = "",
    @SerializedName("scountreleasecount")
    var s_count_release_count: String = "",
    @SerializedName("pcounthospital")
    var p_count_hospital: String = "",
    @SerializedName("pcountdeath")
    var p_count_death: String = "",
    @SerializedName("pcountreleasecount")
    var p_count_release_count: String = "",
    @SerializedName("totalreleasecount")
    val total_release_count:  String = ""
)
