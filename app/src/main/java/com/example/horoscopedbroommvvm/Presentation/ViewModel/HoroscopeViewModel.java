package com.example.horoscopedbroommvvm.Presentation.ViewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.horoscopedbroommvvm.Presentation.Model.HoroscopeDTO;
import com.example.horoscopedbroommvvm.Presentation.Model.ProfileDTO;
import com.example.horoscopedbroommvvm.Presentation.Repository.Network.RetrofitClass;
import com.example.horoscopedbroommvvm.Presentation.Room.HoroscopeRepository;
import com.example.horoscopedbroommvvm.R;

import java.util.List;


public class HoroscopeViewModel extends AndroidViewModel {

    private HoroscopeRepository mHoroscopeRepository;

    private final LiveData<List<HoroscopeDTO>> mAllData;
    private LiveData<List<ProfileDTO>> mAllProfile;
    HoroscopeDTO mData;
    RetrofitClass retrofitClass = new RetrofitClass();
    public HoroscopeViewModel(Application application) {
        super(application);
        mHoroscopeRepository = new HoroscopeRepository(application);
        mAllData = mHoroscopeRepository.getAllData();
        mAllProfile = mHoroscopeRepository.getAllProfile();
    }

    public LiveData<List<ProfileDTO>> getAllProfile() {return mAllProfile;}

    public LiveData<List<HoroscopeDTO>> getAllData() {return mAllData;}


    public void insertProfile(ProfileDTO profileDTO) {mHoroscopeRepository.addProfile(profileDTO);}

    public void insertProfile1(String email, String password){
        ProfileDTO DTO = new ProfileDTO();
        DTO.setEmail(email);
        DTO.setPassword(password);
        insertProfile(DTO);
    }

    public void insert(HoroscopeDTO horoscopeDTO) { mHoroscopeRepository.addHoroscopeData(horoscopeDTO); }

    public void insert1(String date, String zodiac, String info, String fullInfo) {
        HoroscopeDTO DTO = new HoroscopeDTO();
        DTO.setDate(date);
        DTO.setZodiac(zodiac);
        DTO.setInfo(info);
        DTO.setFullInfo(fullInfo);
        insert(DTO);
    }

    public LiveData<HoroscopeDTO> getById(int id){
        return mHoroscopeRepository.getById(id);
    }

    public LiveData<ProfileDTO> getProfile(String email,String password){
        return mHoroscopeRepository.getProfile(email,password);
    }

    public void delete(HoroscopeDTO dto){
        mHoroscopeRepository.deleteHoroscopeData(dto);
    }

    public void update(HoroscopeDTO dto){
        mHoroscopeRepository.updateData(dto);
    }

    public void update1(int id, String date, String zodiac, String info, String fullInfo){
        HoroscopeDTO DTO = new HoroscopeDTO();
        DTO.setId(id);
        DTO.setDate(date);
        DTO.setZodiac(zodiac);
        DTO.setInfo(info);

        DTO.setFullInfo(fullInfo);
        update(DTO);
    }

    public LiveData<String> getPogoda() {return retrofitClass.getPogoda();}
}
