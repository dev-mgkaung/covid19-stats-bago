package gov.mm.covid19statsbago.jsonparsings

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kyawhtut.sheet2json.Sheet2Json
import gov.mm.covid19statsbago.datas.ReturnedPeople
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Mg Kaung on 4/2/2020.
 */

class JsonParsingReturnedPeople {
    fun getResponseForReturnedPeople(
        success: (List<ReturnedPeople>) -> Unit = { _ -> },
        error: (Throwable) -> Unit = {}
    ) {
        Sheet2Json.get("1s0kdwOyNdy77qYufx6_csJLR6bzJ0Ov49WIbg0O237A")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    if (it.isNotEmpty()) {
                        val data = Gson().fromJson<List<ReturnedPeople>>(
                            it,
                            object : TypeToken<List<ReturnedPeople>>() {}.type
                        )
                        success(data)
                    } else {
                        success(mutableListOf())
                    }
                },
                {
                    it.printStackTrace()
                    error(it)
                }
            )
            .isDisposed
    }

    fun getResponseForReturnedPeopleByDate(
        querydate: String,
        success: (List<ReturnedPeople>) -> Unit = { _ -> },
        error: (Throwable) -> Unit = {}
    ) {
        Sheet2Json.get("1s0kdwOyNdy77qYufx6_csJLR6bzJ0Ov49WIbg0O237A", query = querydate)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    if (it.isNotEmpty()) {
                        val data = Gson().fromJson<List<ReturnedPeople>>(
                            it,
                            object : TypeToken<List<ReturnedPeople>>() {}.type
                        )
                        success(data)
                    } else {
                        success(mutableListOf())
                    }
                },
                {
                    it.printStackTrace()
                    error(it)
                }
            )
            .isDisposed
    }

}