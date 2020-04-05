package gov.mm.covid19statsbago.jsonparsings

import gov.mm.covid19statsbago.datas.*
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
        success: (List<CovidCountry>, String) -> Unit = { _, _ -> },
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
                    with((response.body() ?: CovidCountryResponse("", mutableListOf()))) {
                        success(data, date)
                    }
                }
            }
        })
    }


    fun getGlobalStatsDataResponse(
        success: (MutableList<GlobalDataResponse>) -> Unit = {  _ -> },
        error: (Throwable) -> Unit = {}
    ) {
        val retrofit = Retrofit.Builder()
            .baseUrl(ApiInterface.COVID_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val api = retrofit.create(ApiInterface::class.java)
        val call = api.getGlobalDataList()
        call.enqueue(object : Callback<GlobalStatsResponse> {
            override fun onFailure(call: Call<GlobalStatsResponse>, t: Throwable) {
                t.printStackTrace()
                error(t)
            }

            override fun onResponse(
                call: Call<GlobalStatsResponse>,
                response: Response<GlobalStatsResponse>
            ) {
                if (response.isSuccessful) {
                    with((response.body() ?: GlobalStatsResponse( mutableListOf()))) {
                        success(data)
                    }
                }
            }
        })
    }

    fun getMMStatsDataResponse(
        success: (MutableList<MMDataResponse>) -> Unit = {  _ -> },
        error: (Throwable) -> Unit = {}
    ) {
        val retrofit = Retrofit.Builder()
            .baseUrl(ApiInterface.COVID_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val api = retrofit.create(ApiInterface::class.java)
        val call = api.getMMDataList()
        call.enqueue(object : Callback<MMStatsResponse> {
            override fun onFailure(call: Call<MMStatsResponse>, t: Throwable) {
                t.printStackTrace()
                error(t)
            }

            override fun onResponse(
                call: Call<MMStatsResponse>,
                response: Response<MMStatsResponse>
            ) {
                if (response.isSuccessful) {
                    with((response.body() ?: MMStatsResponse( mutableListOf()))) {
                        success(data)
                    }
                }
            }
        })
    }
}