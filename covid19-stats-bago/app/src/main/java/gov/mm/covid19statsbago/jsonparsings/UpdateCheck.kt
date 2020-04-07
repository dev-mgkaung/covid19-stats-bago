package gov.mm.covid19statsbago.jsonparsings

import gov.mm.covid19statsbago.BuildConfig
import gov.mm.covid19statsbago.generals.UPDATE_CHECK_BASE_URL
import gov.mm.covid19statsbago.generals.UpdateStatus
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

/**
 * Created by Mg Kaung on 4/2/2020.
 */
class UpdateCheck {
    fun checkUpdate(
        success: (UpdateStatus) -> Unit = { _ -> },
        error: (Throwable) -> Unit = {}
    ) {
        val retrofit = Retrofit.Builder()
            .baseUrl(UPDATE_CHECK_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val api = retrofit.create(UpdateCheckApi::class.java)
        api.checkUpdate().enqueue(object : Callback<UpdateStatus> {
            override fun onFailure(call: Call<UpdateStatus>, t: Throwable) {
                error(t)
            }

            override fun onResponse(
                call: Call<UpdateStatus>,
                response: Response<UpdateStatus>
            ) {
                if (response.isSuccessful) {
                    success(response.body() ?: UpdateStatus("", BuildConfig.VERSION_NAME, ""))
                }
            }
        })
    }
}

interface UpdateCheckApi {

    @GET("/wyphyoe/covid19-stats-bago/master/sample_json_structure/update-status.json")
    fun checkUpdate(): Call<UpdateStatus>
}
