package gov.mm.covid19statsbago.generals

import gov.mm.covid19statsbago.datas.ReturnedPeopleResponse
import gov.mm.covid19statsbago.datas.TreatmentDataResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Mg Kaung on 4/4/2020.
 */
interface ApiInterfaceTreatment {
    @GET("api?id=1s0kdwOyNdy77qYufx6_csJLR6bzJ0Ov49WIbg0O237A&sheet=2")
    fun getTreatmentDataList(): Call<TreatmentDataResponse>

    @GET("api?id=1s0kdwOyNdy77qYufx6_csJLR6bzJ0Ov49WIbg0O237A&sheet=2")
    fun getTreatmentDataListByDate(@Query("q") q:String): Call<TreatmentDataResponse>
    companion object {
        const val JSONURL = GOOGLESHEET_URL
    }
}