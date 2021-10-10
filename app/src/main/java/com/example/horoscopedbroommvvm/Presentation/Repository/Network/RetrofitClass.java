package com.example.horoscopedbroommvvm.Presentation.Repository.Network;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClass {

    private PogodaApi api;

    public RetrofitClass() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(PogodaApi.class);
    }

    public LiveData<String> getPogoda(){
        MutableLiveData<String> pogoda = new MutableLiveData<>();
        api.getPogoda().enqueue(new Callback<MyPojo>() {
            @Override
            public void onResponse(Call<MyPojo> call, Response<MyPojo> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d("ggssss",call.toString());
                    MyPojo myPojo = response.body();
                    Hourly hourly = myPojo.getHourly()[0];
                    float temp = Float.parseFloat(hourly.getTemp());
                    temp = temp - 273;
                    float clouds = Integer.parseInt(hourly.getClouds());
                    Weather weather = hourly.getWeather()[0];
                    String infoPogoda = weather.getMain();
                    String pressure = hourly.getPressure();
                    float pressureInt = Integer.parseInt(pressure);


                    float superFormulaInt = Math.abs((Math.abs((pressureInt-1025)/100)) - (clouds/100));

                    String superFormula = String.valueOf(superFormulaInt);
                    pogoda.setValue(superFormula);
                }
            }

            @Override
            public void onFailure(Call<MyPojo> call, Throwable t) {
                Log.e("ggssss", t.toString()+ call);
                Log.d("ggssss","123");
            }
        });

        return pogoda;
    }

}
