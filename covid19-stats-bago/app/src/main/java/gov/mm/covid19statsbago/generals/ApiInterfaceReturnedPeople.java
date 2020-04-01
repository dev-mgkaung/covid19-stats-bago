package gov.mm.covid19statsbago.generals;

import retrofit2.Call;
import retrofit2.http.GET;


public interface ApiInterfaceReturnedPeople {
    String JSONURL = ConstantsKt.RETURNED_PEOPLE_JSON_URL;
    @GET("returned_stats.json")
    Call<String> getString();
}