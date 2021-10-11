package com.example.horoscopedbroommvvm.Presentation.Repository.Network;

public class Hourly {

    private String temp;

    private String pressure;

    private String clouds;

    private Weather[] weather;

    public String getTemp ()
    {
        return temp;
    }

    public String getPressure ()
    {
        return pressure;
    }

    public String getClouds ()
    {
        return clouds;
    }

    public Weather[] getWeather ()
    {
        return weather;
    }

}
