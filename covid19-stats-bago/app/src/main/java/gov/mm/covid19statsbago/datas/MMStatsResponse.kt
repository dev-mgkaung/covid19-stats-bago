package gov.mm.covid19statsbago.datas

import com.google.gson.annotations.SerializedName

/**
 * Created by Mg Kaung on 4/6/2020.
 */
data class MMStatsResponse(
@SerializedName("countrydata")
val data: MutableList<MMDataResponse>
)

data class MMDataResponse(
    @SerializedName("total_cases")
    var total_cases: Int = 0,
    @SerializedName("total_recovered")
    var total_recovered: Int = 0,
    @SerializedName("total_deaths")
    var total_deaths: Int = 0,
    @SerializedName("total_new_cases_today")
    var total_new_cases_today: Int = 0,
    @SerializedName("total_new_deaths_today")
    var total_new_deaths_today: Int = 0
)
