package gov.mm.covid19statsbago.datas

/**
 * Created by Mg Kaung on 4/2/2020.
 */
data class Returned(
    var byCountry:ArrayList<Country> = arrayListOf(),
    var total: String=""
)