package gov.mm.covid19statsbago.generals

import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.GET
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Mg Kaung on 4/2/2020.
 */
interface ApiInterface {

    @GET("/countries")
    fun getCovidCountryList(): Call<List<CovidCountry>>

    companion object {
        const val JSONURL = COVID_FOR_MM_JSON_URL
    }
}

data class CovidCountry(
    @SerializedName("country")
    val country: String,
    @SerializedName("countryInfo")
    val countryInfo: CountryInfo,
    @SerializedName("updated")
    val updated: String,
    @SerializedName("cases")
    val cases: String,
    @SerializedName("deaths")
    val deaths: String,
    @SerializedName("recovered")
    val recovered: String
)

data class CountryInfo(
    @SerializedName("_id")
    val id: Int?,
    @SerializedName("iso2")
    val iso2: String?,
    @SerializedName("iso3")
    val iso3: String?,
    @SerializedName("flag")
    val flag: String
)

fun getUpdatedDate(): String =
    SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).format(Date())
        .toUniNumber()

fun List<CovidCountry>.getMyanmar() =
    first { it.country == "Myanmar" && it.countryInfo.iso2 == "MM" && it.countryInfo.iso3 == "MMR" }

fun List<CovidCountry>.getTotalCases() = sumBy { it.cases.toInt() }.toUniNumber()

fun List<CovidCountry>.getTotalDeaths() = sumBy { it.deaths.toInt() }.toUniNumber()

fun List<CovidCountry>.getTotalRecovered() = sumBy { it.recovered.toInt() }.toUniNumber()
