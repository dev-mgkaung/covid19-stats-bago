package gov.mm.covid19statsbago.datas

/**
 * Created by Mg Kaung on 4/2/2020.
 */

data class CovidCountry(
    var Country: String = "",
    var CountrySlug: String = "",
    var NewConfirmed: Int = 0,
    var TotalConfirmed: Int = 0,
    var NewDeaths: Int = 0,
    var TotalDeaths: Int = 0,
    var NewRecovered: Int = 0,
    var TotalRecovered: Int = 1
)