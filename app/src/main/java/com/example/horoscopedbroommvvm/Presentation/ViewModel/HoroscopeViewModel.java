package com.example.horoscopedbroommvvm.Presentation.ViewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.horoscopedbroommvvm.Presentation.Model.HoroscopeDTO;
import com.example.horoscopedbroommvvm.Presentation.Room.HoroscopeRepository;

import java.util.List;


// TODO тут можно поставть просто ViewModel - разобраться (но без androidViewModel не работает super) протестить без super
public class HoroscopeViewModel extends AndroidViewModel {

    private HoroscopeRepository mHoroscopeRepository;

    private final LiveData<List<HoroscopeDTO>> mAllData;

    public HoroscopeViewModel(Application application) {
        super(application);
        mHoroscopeRepository = new HoroscopeRepository(application);
        mAllData = mHoroscopeRepository.getAllData();
    }

    public LiveData<List<HoroscopeDTO>> getAllData() {return mAllData;}

    public void insert(HoroscopeDTO horoscopeDTO) { mHoroscopeRepository.addHoroscopeData(horoscopeDTO); }
}
