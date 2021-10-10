package com.example.horoscopedbroommvvm.Presentation.Repository.Network;

public class MyPojo {

    private String timezone;

    private String timezone_offset;

    private String lon;

    private Hourly[] hourly;


    private String lat;


    public String getTimezone ()
    {
        return timezone;
    }

    public void setTimezone (String timezone)
    {
        this.timezone = timezone;
    }

    public String getTimezone_offset ()
    {
        return timezone_offset;
    }

    public void setTimezone_offset (String timezone_offset)
    {
        this.timezone_offset = timezone_offset;
    }

    public String getLon ()
    {
        return lon;
    }

    public void setLon (String lon)
    {
        this.lon = lon;
    }

    public Hourly[] getHourly ()
    {
        return hourly;
    }

    public void setHourly (Hourly[] hourly)
    {
        this.hourly = hourly;
    }


    public String getLat ()
    {
        return lat;
    }

    public void setLat (String lat)
    {
        this.lat = lat;
    }


}
