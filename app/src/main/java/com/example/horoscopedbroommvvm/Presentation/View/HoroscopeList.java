package com.example.horoscopedbroommvvm.Presentation.View;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.horoscopedbroommvvm.Presentation.Model.HoroscopeDTO;
import com.example.horoscopedbroommvvm.Presentation.View.Adapter.HoroscopeRVAdapter;
import com.example.horoscopedbroommvvm.Presentation.ViewModel.HoroscopeViewModel;
import com.example.horoscopedbroommvvm.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class HoroscopeList extends Fragment {

    private HoroscopeViewModel mViewModel;
    private View mView;
    RecyclerView recyclerView1;
    FloatingActionButton fab;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_horoscope_list, container, false);

        recyclerView1 = mView.findViewById(R.id.HoroscopeRecyclerView);
        fab = mView.findViewById(R.id.fab);

        fab.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_horoscopeList_to_addHoroscopeData));
        recyclerView1.setLayoutManager(new LinearLayoutManager(getContext()));
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(HoroscopeViewModel.class);

        mViewModel.getAllData().observe(getViewLifecycleOwner(),(List<HoroscopeDTO> horoscopeList)->{
            RecyclerView recyclerView = mView.findViewById(R.id.HoroscopeRecyclerView);
            recyclerView.setAdapter(new HoroscopeRVAdapter(horoscopeList));
        });
    }
}