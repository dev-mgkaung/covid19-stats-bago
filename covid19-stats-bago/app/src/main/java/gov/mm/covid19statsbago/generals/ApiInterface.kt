package gov.mm.covid19statsbago.generals

import gov.mm.covid19statsbago.datas.CovidCountryResponse
import gov.mm.covid19statsbago.datas.GlobalDataResponse
import gov.mm.covid19statsbago.datas.GlobalStatsResponse
import gov.mm.covid19statsbago.datas.MMStatsResponse
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by Mg Kaung on 4/2/2020.
 */
interface ApiInterface {

    @GET("summary")
    fun getCovidCountryList(): Call<CovidCountryResponse>


    @GET("free-api?global=stats")
    fun getGlobalDataList(): Call<GlobalStatsResponse>


    @GET("free-api?countryTotal=MM")
    fun getMMDataList(): Call<MMStatsResponse>

    companion object {
        const val JSONURL = COVID_FOR_MM_JSON_URL
        const val COVID_BASE_URL= VIRUSTRACKER_URL
    }
}