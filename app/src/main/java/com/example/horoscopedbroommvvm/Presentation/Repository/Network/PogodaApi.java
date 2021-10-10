package com.example.horoscopedbroommvvm.Presentation.Repository.Network;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


public interface PogodaApi {
    @GET("onecall?lat=55.751125&lon=37.617831&exclude=daily&appid=8abc715c188b044ea7f2c6716f2f904f")
    Call<MyPojo> getPogoda();
}
