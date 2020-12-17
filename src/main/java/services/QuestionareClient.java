package services;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


//QuestionareClient builder

//Created by Tharuka Gamage on 20/11/20.
//Copyright © 2020 Tharuka Gamage. All rights reserved.

public class QuestionareClient {

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://localhost:8080")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public QuestionareService getService(){
        return retrofit.create(QuestionareService.class);
    }
}
