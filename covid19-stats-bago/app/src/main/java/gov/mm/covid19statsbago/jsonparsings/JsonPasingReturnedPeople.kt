package gov.mm.covid19statsbago.jsonparsings

import android.util.Log
import gov.mm.covid19statsbago.datas.Country
import gov.mm.covid19statsbago.datas.Returned
import gov.mm.covid19statsbago.datas.ReturnedPeople
import gov.mm.covid19statsbago.datas.ReturnedPeopleDataSet
import gov.mm.covid19statsbago.generals.ApiInterfaceReturnedPeople
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

/**
 * Created by Mg Kaung on 4/2/2020.
 */

class JsonPasingReturnedPeople {
    public fun getResponseForReturnedPeople(): ArrayList<ReturnedPeople>? {
        var returnedPeopleDataSetList : ArrayList<ReturnedPeople> ? =  ArrayList<ReturnedPeople>()

        val retrofit = Retrofit.Builder()
            .baseUrl(ApiInterfaceReturnedPeople.JSONURL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
        val api = retrofit.create(ApiInterfaceReturnedPeople::class.java)
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
                            //getting the whole json object from the response
                            val obj = JSONObject(jsonresponse)
                            val dataArray = obj.getJSONArray("returnedPeople")
                            for (jsonIndex in 0..dataArray.length() - 1) {
                                var returnedPeople : ReturnedPeople= ReturnedPeople()
                                var dataobj = dataArray.getJSONObject(jsonIndex)
                                returnedPeople.id=dataobj.getString("id").toString()
                                returnedPeople.district=dataobj.getString("district").toString()
                                returnedPeople.township=dataobj.getString("township").toString()
                                returnedPeople.date=dataobj.getString("date").toString()
                                var returnedPeopledataobj=dataobj.getJSONObject("returned")
                                val datareturned: Returned= Returned()
                                datareturned.total=returnedPeopledataobj.get("total").toString()
                                val returnedCountryDataArray = returnedPeopledataobj.getJSONArray("byCountry")
                                var countryDataSetList : ArrayList<Country> ? =  ArrayList<Country>()
                                for(innerjsonIndex in 0..returnedCountryDataArray.length() - 1)
                                {
                                    var country: Country= Country()
                                    var countrydataobj = dataArray.getJSONObject(innerjsonIndex)
                                    country.country=countrydataobj.getString("country").toString()
                                    country.total=countrydataobj.getString("total").toString()
                                    countryDataSetList?.add(country)
                                }
                                if (countryDataSetList != null) {
                                    datareturned.byCountry=countryDataSetList
                                };

                                returnedPeople.returned=datareturned
                                returnedPeople.suspicion=dataobj.getString("suspicion").toString()
                                returnedPeople.remark=dataobj.getString("remark").toString()
                                returnedPeopleDataSetList?.add(returnedPeople)
                            }

                        } catch (e: JSONException) {
                            e.printStackTrace()
                        }
                    } else {
                        Log.i(
                            "onEmptyResponse",
                            "Returned empty response"
                        )//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }

            }

            override fun onFailure(call: retrofit2.Call<String>, t: Throwable) {

            }
        })
        return returnedPeopleDataSetList
    }

}