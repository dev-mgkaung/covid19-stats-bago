package gov.mm.covid19statsbago.jsonparsings

import android.util.Log
import android.widget.TextView
import gov.mm.covid19statsbago.datas.*
import gov.mm.covid19statsbago.generals.ApiInterface
import gov.mm.covid19statsbago.generals.ApiInterfaceReturnedPeople
import gov.mm.covid19statsbago.generals.UtilFont
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

/**
 * Created by Mg Kaung on 4/2/2020.
 */
class JsonPasingDashboardList {
      fun getResponseForDashboard( date:TextView,globalconfirm:TextView,globaldeath:TextView,globalrecover:TextView,mmconfirm:TextView,mmdeath:TextView,mmrecover:TextView) {

        var globalTotalDeathCount:Int =0
        var globalTotalConfirmedCount:Int =0
        var globalTotalRecoverCount:Int =0;
        val retrofit = Retrofit.Builder()
            .baseUrl(ApiInterface.JSONURL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
        val api = retrofit.create(ApiInterface::class.java)
        val call = api.getString()
        call.enqueue(object : retrofit2.Callback<String> {
            override fun onResponse(
                call: retrofit2.Call<String>,
                response: retrofit2.Response<String>
            ) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        val jsonresponse = response.body().toString()
                        try {
                            val obj = JSONObject(jsonresponse)

                            date.text=obj.getString("Date").toString()

                            val dataArray = obj.getJSONArray("Countries")
                            for (jsonIndex in 0..dataArray.length() - 1) {
                                var covidCountry : CovidCountry = CovidCountry()
                                var dataobj = dataArray.getJSONObject(jsonIndex)

                                covidCountry.Country=dataobj.getString("Country").toString()
                                covidCountry.CountrySlug=dataobj.getString("Slug").toString()
                                covidCountry.NewConfirmed=dataobj.getString("NewConfirmed").toInt()
                                covidCountry.TotalConfirmed=dataobj.getString("TotalConfirmed").toInt()
                                covidCountry.NewDeaths=dataobj.getString("NewDeaths").toInt()
                                covidCountry.TotalDeaths=dataobj.getString("TotalDeaths").toInt()
                                covidCountry.NewRecovered=dataobj.getString("NewRecovered").toInt()
                                covidCountry.TotalRecovered=dataobj.getString("TotalRecovered").toInt()
                                globalTotalConfirmedCount+=covidCountry.TotalConfirmed;
                                globalTotalDeathCount+=covidCountry.TotalDeaths;
                                globalTotalRecoverCount+=covidCountry.TotalRecovered;
                                if(dataobj.getString("Country").toString().equals("Burma"))
                                {
                                    mmconfirm.text= UtilFont.convertUniNumber(covidCountry.TotalConfirmed.toString())
                                    mmdeath.text= UtilFont.convertUniNumber(covidCountry.TotalDeaths.toString())
                                    mmrecover.text= UtilFont.convertUniNumber(covidCountry.TotalRecovered.toString())
                                }
                            }

                            globalconfirm.text= UtilFont.convertUniNumber(globalTotalConfirmedCount.toString())
                            globaldeath.text= UtilFont.convertUniNumber(globalTotalDeathCount.toString())
                            globalrecover.text= UtilFont.convertUniNumber(globalTotalRecoverCount.toString())

                        } catch (e: JSONException) {
                            e.printStackTrace()
                        }
                    } else {
                    }
                }

            }

            override fun onFailure(call: retrofit2.Call<String>, t: Throwable) {

            }
        })
       // return dashboardData
    }
}