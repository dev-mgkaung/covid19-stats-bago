package gov.mm.covid19statsbago.datas

import com.google.gson.annotations.SerializedName

/**
 * Created by Mg Kaung on 4/2/2020.
 */

data class CovidCountryResponse(
    @SerializedName("Countries")
    val data: List<CovidCountry>
)

data class CovidCountry(
    @SerializedName("Country")
    var country: String = "",
    @SerializedName("Slug")
    var countrySlug: String = "",
    @SerializedName("NewConfirmed")
    var newConfirmed: Int = 0,
    @SerializedName("TotalConfirmed")
    var totalConfirmed: Int = 0,
    @SerializedName("NewDeaths")
    var newDeaths: Int = 0,
    @SerializedName("TotalDeaths")
    var totalDeaths: Int = 0,
    @SerializedName("NewRecovered")
    var newRecovered: Int = 0,
    @SerializedName("TotalRecovered")
    var totalRecovered: Int = 0
)