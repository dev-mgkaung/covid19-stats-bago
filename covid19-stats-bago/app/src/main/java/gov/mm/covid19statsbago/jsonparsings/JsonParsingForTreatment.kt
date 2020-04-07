package gov.mm.covid19statsbago.jsonparsings

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kyawhtut.sheet2json.Sheet2Json
import gov.mm.covid19statsbago.datas.QurantineData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Mg Kaung on 4/4/2020.
 */
class JsonParsingForTreatment {

    fun getResponseForReturnedPeople(
        success: (List<QurantineData>) -> Unit = { _ -> },
        error: (Throwable) -> Unit = {}
    ) {
        Sheet2Json.get("1s0kdwOyNdy77qYufx6_csJLR6bzJ0Ov49WIbg0O237A", sheetNumber = 2)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    if (it.isNotEmpty()) {
                        val data = Gson().fromJson<List<QurantineData>>(
                            it,
                            object : TypeToken<List<QurantineData>>() {}.type
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
        success: (List<QurantineData>) -> Unit = { _ -> },
        error: (Throwable) -> Unit = {}
    ) {
        Sheet2Json.get(
            "1s0kdwOyNdy77qYufx6_csJLR6bzJ0Ov49WIbg0O237A",
            sheetNumber = 2,
            query = querydate
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    if (it.isNotEmpty()) {
                        val data = Gson().fromJson<List<QurantineData>>(
                            it,
                            object : TypeToken<List<QurantineData>>() {}.type
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
