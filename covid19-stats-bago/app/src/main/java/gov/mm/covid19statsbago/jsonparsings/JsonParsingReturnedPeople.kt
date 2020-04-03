package gov.mm.covid19statsbago.jsonparsings

import android.util.Log
import gov.mm.covid19statsbago.datas.*
import gov.mm.covid19statsbago.generals.ApiInterfaceForRP
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Mg Kaung on 4/2/2020.
 */

class JsonParsingReturnedPeople {
    fun getResponseForReturnedPeople(
        success: (List<ReturnedPeople>) -> Unit = {  _ -> },
        error: (Throwable) -> Unit = {}
    ) {
        val retrofit = Retrofit.Builder()
            .baseUrl(ApiInterfaceForRP.JSONURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val api = retrofit.create(ApiInterfaceForRP::class.java)
        val call = api.getReturnedPeopleList()

        call.enqueue(object : Callback<ReturnedPeopleResponse> {
            override fun onFailure(call: Call<ReturnedPeopleResponse>, t: Throwable) {
                t.printStackTrace()
                Log.e("aaao",t.toString())
                error(t)
            }
            override fun onResponse(
                call: Call<ReturnedPeopleResponse>,
                response: Response<ReturnedPeopleResponse>
            ) {
                if (response.isSuccessful) {
                    with((response.body() ?: ReturnedPeopleResponse( mutableListOf()))) {
                        Log.e("dd=",data.size.toString())
                        success(data)
                    }
                }else{ Log.e("ff=","Fail")}
            }
        })
    }

}