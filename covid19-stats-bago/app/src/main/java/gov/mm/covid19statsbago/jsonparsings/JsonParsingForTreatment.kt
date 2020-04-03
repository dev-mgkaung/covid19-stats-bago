package gov.mm.covid19statsbago.jsonparsings

import android.util.Log
import gov.mm.covid19statsbago.datas.QurantineData
import gov.mm.covid19statsbago.datas.ReturnedPeople
import gov.mm.covid19statsbago.datas.TreatmentDataResponse
import gov.mm.covid19statsbago.generals.ApiInterfaceTreatment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Mg Kaung on 4/4/2020.
 */
class JsonParsingForTreatment {

    fun getResponseForReturnedPeople(
        success: (List<QurantineData>) -> Unit = { _ -> },
        error: (Throwable) -> Unit = {}
    ) {
        val retrofit = Retrofit.Builder()
            .baseUrl(ApiInterfaceTreatment.JSONURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val api = retrofit.create(ApiInterfaceTreatment::class.java)
        val call = api.getTreatmentDataList()

        call.enqueue(object : Callback<TreatmentDataResponse> {
            override fun onFailure(call: Call<TreatmentDataResponse>, t: Throwable) {
                t.printStackTrace()
                Log.e("aaao", t.toString())
                error(t)
            }

            override fun onResponse(
                call: Call<TreatmentDataResponse>,
                response: Response<TreatmentDataResponse>
            ) {
                if (response.isSuccessful) {
                    with((response.body() ?: TreatmentDataResponse(mutableListOf()))) {
                        Log.e("dd=", datas.size.toString())
                        success(datas)
                    }
                } else {
                    Log.e("ff=", "Fail")
                }
            }
        })
    }

}
