package gov.mm.covid19statsbago.generals

import gov.mm.covid19statsbago.datas.ReturnedPeopleResponse
import gov.mm.covid19statsbago.datas.TreatmentDataResponse
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by Mg Kaung on 4/4/2020.
 */
interface ApiInterfaceTreatment {
    @GET("quarantine_stats.json")
    fun getTreatmentDataList(): Call<TreatmentDataResponse>

    companion object {
        const val JSONURL = TREATMENT_JSON_URL
    }
}