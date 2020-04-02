package gov.mm.covid19statsbago.generals

import gov.mm.covid19statsbago.datas.CovidCountryResponse
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by Mg Kaung on 4/2/2020.
 */
interface ApiInterface {

    @GET("summary")
    fun getCovidCountryList(): Call<CovidCountryResponse>

    companion object {
        const val JSONURL = COVID_FOR_MM_JSON_URL
    }
}