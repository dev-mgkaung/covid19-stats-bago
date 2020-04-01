package gov.mm.covid19statsbago.generals;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Mg Kaung on 4/2/2020.
 */

public interface ApiInterface {
    String JSONURL = ConstantsKt.COVID_FOR_MM_JSON_URL;
    @GET("summary")
    Call<String> getString();
}