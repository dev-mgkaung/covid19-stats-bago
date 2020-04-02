package gov.mm.covid19statsbago.jsonparsings

import gov.mm.covid19statsbago.datas.CovidCountry
import gov.mm.covid19statsbago.datas.CovidCountryResponse
import gov.mm.covid19statsbago.generals.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Mg Kaung on 4/2/2020.
 */
class JsonParsingDashboardList {
    fun getResponseForDashboard(
        success: (List<CovidCountry>) -> Unit = {},
        error: (Throwable) -> Unit = {}
    ) {
        val retrofit = Retrofit.Builder()
            .baseUrl(ApiInterface.JSONURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val api = retrofit.create(ApiInterface::class.java)
        val call = api.getCovidCountryList()
        call.enqueue(object : Callback<CovidCountryResponse> {
            override fun onFailure(call: Call<CovidCountryResponse>, t: Throwable) {
                t.printStackTrace()
                error(t)
            }

            override fun onResponse(
                call: Call<CovidCountryResponse>,
                response: Response<CovidCountryResponse>
            ) {
                if (response.isSuccessful) {
                    success((response.body() ?: CovidCountryResponse(mutableListOf())).data)
                }
            }
        })
    }
}