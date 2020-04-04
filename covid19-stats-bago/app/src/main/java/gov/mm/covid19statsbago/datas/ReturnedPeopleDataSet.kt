package gov.mm.covid19statsbago.datas

import com.google.gson.annotations.SerializedName

/**
 * Created by Mg Kaung on 4/2/2020.
 */

data class ReturnedPeopleResponse(
    @SerializedName("returned_stats")
    val data: List<ReturnedPeople>
)

data class ReturnedPeople(
    @SerializedName("id")
    var id: String = "",
    @SerializedName("district")
    var district: String = "",
    @SerializedName("township")
    var township: String = "",
    @SerializedName("date")
    var date: String = "",
    @SerializedName("returned")
    var returned: Returned,
    @SerializedName("suspicion")
    var suspicion: String = "",
    @SerializedName("remark")
    var remark: String = ""
)

data class Returned(
    @SerializedName("byCountry")
    var byCountry: List<Country> ,
    @SerializedName("total")
    var total: String = ""
)

data class Country(
    @SerializedName("country")
    var country: String = "",
    @SerializedName("total")
    var total: String = ""
)