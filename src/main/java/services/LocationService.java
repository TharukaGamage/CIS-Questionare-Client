package services;

import models.RegionList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;

import java.util.Map;

public interface LocationService {
        @Headers("Content-Type: application/json")
        @GET("v1/geo/countries/SL/regions")
        Call<RegionList> getRegionList(@HeaderMap Map<String, String> headers);
}
