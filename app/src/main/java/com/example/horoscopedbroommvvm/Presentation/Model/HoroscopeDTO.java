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

}
