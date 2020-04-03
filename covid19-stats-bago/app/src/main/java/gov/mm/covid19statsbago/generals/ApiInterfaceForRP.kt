package gov.mm.covid19statsbago.generals

import gov.mm.covid19statsbago.datas.ReturnedPeopleResponse
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by Mg Kaung on 4/3/2020.
 */
interface ApiInterfaceForRP {
    @GET("returned_stats.json")
    fun getReturnedPeopleList(): Call<ReturnedPeopleResponse>

    companion object {
        const val JSONURL = RETURNED_PEOPLE_JSON_URL
    }
}