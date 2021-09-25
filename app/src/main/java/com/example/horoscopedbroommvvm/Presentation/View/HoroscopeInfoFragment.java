package com.example.horoscopedbroommvvm.Presentation.View;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.horoscopedbroommvvm.Presentation.Model.HoroscopeDTO;
import com.example.horoscopedbroommvvm.Presentation.View.Adapter.HoroscopeRVAdapter;
import com.example.horoscopedbroommvvm.Presentation.ViewModel.HoroscopeViewModel;
import com.example.horoscopedbroommvvm.R;

import java.util.List;

public class HoroscopeInfoFragment extends Fragment {


    HoroscopeViewModel mViewModel;
    HoroscopeDTO DTO;
    private View mView;
    ImageButton bck;
    String date,info,zodiac;
    TextView tvdate,tvinfo,tvzodiac;
    int id;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id = getArguments().getInt("id");
    }
    @Override

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_horoscope_info, container, false);

        mViewModel = new ViewModelProvider(this).get(HoroscopeViewModel.class);

        mViewModel.getById(id).observe(getViewLifecycleOwner(),
                (HoroscopeDTO horoscopeDTO) -> {
                    DTO = horoscopeDTO;
                    Log.d("addd", "onCreate: "+ DTO.toString());
                });

        bck = mView.findViewById(R.id.back_button);
        bck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).popBackStack();
            }
        });


        id = getArguments().getInt("id");



        tvdate = mView.findViewById(R.id.textViewDate_info);

        tvinfo = mView.findViewById(R.id.textViewMiniInfo_info);

        tvzodiac = mView.findViewById(R.id.textViewNameZodiac_info);

        return mView;
    }
}