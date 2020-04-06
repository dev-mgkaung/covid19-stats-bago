package gov.mm.covid19statsbago.datas

import com.google.gson.annotations.SerializedName

/**
 * Created by Mg Kaung on 4/6/2020.
 */
data class GlobalStatsResponse(
    @SerializedName("results")
    val data: MutableList<GlobalDataResponse>
)

data class GlobalDataResponse(
    @SerializedName("total_cases")
    var total_cases: Int = 0,
    @SerializedName("total_recovered")
    var total_recovered: Int = 0,
    @SerializedName("total_unresolved")
    var total_unresolved: Int = 0,
    @SerializedName("total_deaths")
    var total_deaths: Int = 0,
    @SerializedName("total_new_cases_today")
    var total_new_cases_today: Int = 0,
    @SerializedName("total_new_deaths_today")
    var total_new_deaths_today: Int = 0,
    @SerializedName("total_active_cases")
    var total_active_cases: Int = 0,
    @SerializedName("total_serious_cases")
    var total_serious_cases: Int = 0,
    @SerializedName("total_affected_countries")
    var total_affected_countries: Int = 0
)
