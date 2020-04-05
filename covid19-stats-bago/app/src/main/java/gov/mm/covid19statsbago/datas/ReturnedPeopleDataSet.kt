package gov.mm.covid19statsbago.datas

import com.google.gson.annotations.SerializedName

/**
 * Created by Mg Kaung on 4/2/2020.
 */

data class ReturnedPeopleResponse(
    @SerializedName("rows")
    val data: List<ReturnedPeople>
)

data class ReturnedPeople(
    @SerializedName("id")
    var id: String = "",
    @SerializedName("date")
    var date: String = "",
    @SerializedName("district")
    var district: String = "",
    @SerializedName("township")
    var township: String = "",
    @SerializedName("china")
    var china: String,
    @SerializedName("laro")
    var laro: String = "",
    @SerializedName("thai")
    var thai: String = "",
    @SerializedName("american")
    var american: String = "",
    @SerializedName("malaysia")
    var malaysia: String = "",
    @SerializedName("singapore")
    var singapore: String,
    @SerializedName("japan")
    var japan: String = "",
    @SerializedName("koera")
    var koera: String = "",
    @SerializedName("india")
    var india: String = "",
    @SerializedName("russia")
    var russia: String = "",
    @SerializedName("england")
    var england: String,
    @SerializedName("myawaddy")
    var myawaddy: String = "",
    @SerializedName("other")
    var other: String = "",
    @SerializedName("total")
    var total: String,
    @SerializedName("suspicion")
    var suspicion: String = "",
    @SerializedName("remark")
    var remark: String = ""
)
