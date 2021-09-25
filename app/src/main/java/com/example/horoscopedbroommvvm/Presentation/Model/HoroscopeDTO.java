package com.example.horoscopedbroommvvm.Presentation.Model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "myTable")
public class HoroscopeDTO {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String date;

    public String zodiac;


    public String info;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getZodiac() {
        return zodiac;
    }

    public void setZodiac(String zodiac) {
        this.zodiac = zodiac;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }


    @Override
    public String toString() {
        return "HoroscopeDTO{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", zodiac='" + zodiac + '\'' +
                ", info='" + info + '\'' +
                '}';
    }
}
