package services;

import models.RegionList;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;

import java.util.Map;

public class GeoLocationClient {
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://wft-geo-db.p.rapidapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public LocationService getService(){ return retrofit.create(LocationService.class); }
}


