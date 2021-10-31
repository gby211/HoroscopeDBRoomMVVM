package com.example.horoscopedbroommvvm.Presentation.Room.DAO;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.horoscopedbroommvvm.Presentation.Model.HoroscopeDTO;
import com.example.horoscopedbroommvvm.Presentation.Model.ProfileDTO;

import java.util.List;

@Dao
public interface HoroscopeDAO {

    @Insert
    void addInfo(HoroscopeDTO info);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addProfile(ProfileDTO info);

    @Delete
    void deleteInfo(HoroscopeDTO info);

    @Query("SELECT * FROM myTable")
    LiveData<List<HoroscopeDTO>> getAllData();

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update1(HoroscopeDTO info);

    @Query("SELECT * FROM myTable WHERE id = :id")
    LiveData<HoroscopeDTO> getById(int id);

    @Query("SELECT * FROM profileTable")
    LiveData<List<ProfileDTO>> getAllProfile();

    @Query("SELECT * FROM profileTable WHERE email = :email AND password = :password")
    LiveData<ProfileDTO> getProfile(String email, String password);
}
