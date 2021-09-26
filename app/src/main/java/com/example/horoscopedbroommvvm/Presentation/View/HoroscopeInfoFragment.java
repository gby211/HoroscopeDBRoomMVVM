package com.example.horoscopedbroommvvm.Presentation.View;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.horoscopedbroommvvm.MainActivity;
import com.example.horoscopedbroommvvm.Presentation.Model.HoroscopeDTO;
import com.example.horoscopedbroommvvm.Presentation.ViewModel.HoroscopeViewModel;
import com.example.horoscopedbroommvvm.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.security.acl.Owner;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class HoroscopeInfoFragment extends Fragment {


    private static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 100;

    HoroscopeViewModel mViewModel;
    LiveData<HoroscopeDTO> DTO;
    HoroscopeDTO DTOnoLiveData;
    private View mView;
    ImageButton bck,shareBtn;
    String date, info, zodiac;
    TextView tvdate, tvinfo, tvzodiac;
    FloatingActionButton fab;
    int id;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id = getArguments().getInt("id");
        Log.d("ggs", "onCreateView: " + id);
    }

    @SuppressLint("FragmentLiveDataObserve")
    @Override

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mViewModel = new ViewModelProvider(this).get(HoroscopeViewModel.class);

//        mViewModel.getById(id).observe(getViewLifecycleOwner(),
//                (HoroscopeDTO horoscopeDTO) -> {
//            DTOnoLiveData = horoscopeDTO;
//        });
        id = getArguments().getInt("id");
        Log.d("ggs", "onCreateView: " + id);
        mViewModel
                .getById(id)
                .observe(  this /*getViewLifecycleOwner()*/,
                        (HoroscopeDTO horoscopeDTO)->{
                            tvdate.setText(horoscopeDTO.getDate());
                            tvinfo.setText(horoscopeDTO.getInfo());
                            tvzodiac.setText(horoscopeDTO.getZodiac());
                            fab.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    NavController navController = Navigation.findNavController(getActivity(),
                                            R.id.nav_host_fragment);
                                    Bundle bundle = new Bundle();
                                    bundle.putString("info",horoscopeDTO.getInfo());
                                    navController.navigate(R.id.action_horoscopeInfoFragment_to_phoneCallFragment, bundle);

                                }
                            });
                        });


        ((MainActivity) getActivity()).findViewById(R.id.share_button).setOnClickListener((View v) -> {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "https://pepa.pepiga_company/" + id);
            sendIntent.setType("text/plain");

            Intent shareIntent = Intent.createChooser(sendIntent, null);
            startActivity(shareIntent);
        });

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_horoscope_info, container, false);
        tvdate = mView.findViewById(R.id.textViewDate_info);
        tvinfo = mView.findViewById(R.id.textViewMiniInfo_info);
        tvzodiac = mView.findViewById(R.id.textViewNameZodiac_info);
        shareBtn = mView.findViewById(R.id.share_button);
        fab = mView.findViewById(R.id.floatingActionButton);
        bck = mView.findViewById(R.id.back_button);






        bck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_horoscopeInfoFragment_to_horoscopeList);
            }
        });
        return mView;
    }
}