package com.ardovic.studyjam2018task;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST("/ALFAJMB/gate")
    Call<CurrencyData> getCurrencyData(@Body CurrenciesRequestData request, @HeaderMap Map<String, String> headers);


}
