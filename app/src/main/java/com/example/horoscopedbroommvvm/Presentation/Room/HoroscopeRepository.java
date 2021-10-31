package com.example.horoscopedbroommvvm.Presentation.Room;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.horoscopedbroommvvm.Presentation.Model.HoroscopeDTO;
import com.example.horoscopedbroommvvm.Presentation.Model.ProfileDTO;
import com.example.horoscopedbroommvvm.Presentation.Room.DAO.HoroscopeDAO;

import java.util.List;

public class HoroscopeRepository {

    private HoroscopeDAO mHoroscopeDAO;
    private LiveData<List<HoroscopeDTO>> mAllData;
    private LiveData<List<ProfileDTO>> mAllProfile;
    private LiveData<HoroscopeDTO> mData;
    private LiveData<ProfileDTO> mProdile;

    public HoroscopeRepository(Application application) {
        HoroscopeDatabase db = HoroscopeDatabase.getDatabase(application);
        mHoroscopeDAO = db.horoscopeDAO();
        mAllData = mHoroscopeDAO.getAllData();
        mAllProfile = mHoroscopeDAO.getAllProfile();
    }

    public LiveData<List<HoroscopeDTO>> getAllData() {
        return mAllData;
    }

    public LiveData<List<ProfileDTO>> getAllProfile() {
        return mAllProfile;
    }

    public void addHoroscopeData(HoroscopeDTO horoscopeDTO) {
        HoroscopeDatabase.databaseWriteExecutor.execute(() -> {
            mHoroscopeDAO.addInfo(horoscopeDTO);
        });
    }

    public void addProfile(ProfileDTO profileDTO) {
        HoroscopeDatabase.databaseWriteExecutor.execute(() -> {
            mHoroscopeDAO.addProfile(profileDTO);
        });
    }

    public void deleteHoroscopeData(HoroscopeDTO horoscopeDTO) {
        HoroscopeDatabase.databaseWriteExecutor.execute(() -> {
            mHoroscopeDAO.deleteInfo(horoscopeDTO);
        });
    }

    public void updateData(HoroscopeDTO horoscopeDTO) {
        HoroscopeDatabase.databaseWriteExecutor.execute(() -> {
            mHoroscopeDAO.update1(horoscopeDTO);
        });
    }

    public LiveData<HoroscopeDTO> getById(int id) {
        mData = mHoroscopeDAO.getById(id);

        return mData;
    }

    public LiveData<ProfileDTO> getProfile(String email, String password){
        mProdile = mHoroscopeDAO.getProfile(email,password);

        return mProdile;
    }

}
