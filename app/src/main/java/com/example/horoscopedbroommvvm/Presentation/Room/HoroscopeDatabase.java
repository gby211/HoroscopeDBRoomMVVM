package com.example.horoscopedbroommvvm.Presentation.Room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.horoscopedbroommvvm.Presentation.Model.HoroscopeDTO;
import com.example.horoscopedbroommvvm.Presentation.Model.ProfileDTO;
import com.example.horoscopedbroommvvm.Presentation.Room.DAO.HoroscopeDAO;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {HoroscopeDTO.class, ProfileDTO.class}, version = 1, exportSchema = false)
public abstract class HoroscopeDatabase extends RoomDatabase {

    public abstract HoroscopeDAO horoscopeDAO();

    private static volatile HoroscopeDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static HoroscopeDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (HoroscopeDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            HoroscopeDatabase.class, "word_database")
                            .build();
                }
            }

        }
        return INSTANCE;
    }
}
