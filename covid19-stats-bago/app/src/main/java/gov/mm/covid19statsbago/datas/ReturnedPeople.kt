package gov.mm.covid19statsbago.datas

/**
 * Created by Mg Kaung on 4/2/2020.
 */
data class ReturnedPeople(
    var id:String="",
    var district: String="",
    var township:String="",
    var date: String="",
    var returned: Returned?=null,
    var suspicion: String="",
    var remark: String=""
)