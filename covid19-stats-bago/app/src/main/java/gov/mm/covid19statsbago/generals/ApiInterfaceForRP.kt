package gov.mm.covid19statsbago.generals

import gov.mm.covid19statsbago.datas.ReturnedPeopleResponse
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by Mg Kaung on 4/3/2020.
 */
interface ApiInterfaceForRP {

    @GET("api?id=1s0kdwOyNdy77qYufx6_csJLR6bzJ0Ov49WIbg0O237A&sheet=1")
    fun getReturnedPeopleList(): Call<ReturnedPeopleResponse>

    companion object {
        const val JSONURL = GOOGLESHEET_URL
    }
}