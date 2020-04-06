package gov.mm.covid19statsbago.jsonparsings

import gov.mm.covid19statsbago.generals.ApiInterface
import gov.mm.covid19statsbago.generals.CovidCountry
import gov.mm.covid19statsbago.generals.getUpdatedDate
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
        success: (List<CovidCountry>, String) -> Unit = { _, _ -> },
        error: (Throwable) -> Unit = {}
    ) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://corona.lmao.ninja")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val api = retrofit.create(ApiInterface::class.java)
        val call = api.getCovidCountryList()
        call.enqueue(object : Callback<List<CovidCountry>> {
            override fun onFailure(call: Call<List<CovidCountry>>, t: Throwable) {
                t.printStackTrace()
                error(t)
            }

            override fun onResponse(
                call: Call<List<CovidCountry>>,
                response: Response<List<CovidCountry>>
            ) {
                if (response.isSuccessful) {
                    with((response.body() ?: mutableListOf())) {
                        success(this, getUpdatedDate())
                    }
                }
            }
        })
    }
}