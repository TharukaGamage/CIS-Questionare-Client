package services;

import models.*;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

import java.util.List;

//Created by Tharuka Gamage on 20/11/20.
//Copyright © 2020 Tharuka Gamage. All rights reserved.

public interface QuestionareService {

//    login request
    @POST("/login")
    @Headers("Content-Type: application/json")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

//    signup request
    @POST("/sign-up")
    @Headers("Content-Type: application/json")
    Call<SignUpResponse> SignUp(@Body SignUpRequest signUpRequest);

//    save answers request
    @POST("/save-answers")
    @Headers("Content-Type: application/json")
    Call<BaseResponse> SaveAnswers(@Body Questionare questionare);

//    get answers request
    @POST("/get-answers")
    @Headers("Content-Type: application/json")
    Call<List<Questionare>> getAnswers(@Body String token);
}
