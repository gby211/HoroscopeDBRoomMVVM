package com.example.horoscopedbroommvvm.Presentation.View;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
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

public class HoroscopeListFragment extends Fragment {

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

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView,
                                  @NonNull RecyclerView.ViewHolder viewHolder,
                                  @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                mViewModel.delete(((HoroscopeRVAdapter) recyclerView1.getAdapter()).getData().get(position));
            }
        }).attachToRecyclerView(recyclerView1);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView,
                                  @NonNull RecyclerView.ViewHolder viewHolder,
                                  @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                NavController navController = Navigation.findNavController(getActivity(),
                        R.id.nav_host_fragment);
                Bundle bundle = new Bundle();
                bundle.putInt("id",((HoroscopeRVAdapter) recyclerView1.getAdapter()).getData().get(position).getId());
                bundle.putString("zod",((HoroscopeRVAdapter) recyclerView1.getAdapter()).getData().get(position).getZodiac());
                bundle.putString("inf",((HoroscopeRVAdapter) recyclerView1.getAdapter()).getData().get(position).getInfo());
                bundle.putString("date",((HoroscopeRVAdapter) recyclerView1.getAdapter()).getData().get(position).getDate());
                navController.navigate(R.id.action_horoscopeList_to_updateFragment, bundle);
            }
        }).attachToRecyclerView(recyclerView1);

        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(HoroscopeViewModel.class);

        mViewModel.getAllData().observe(getViewLifecycleOwner(),
                (List<HoroscopeDTO> horoscopeList) -> {
            RecyclerView recyclerView = mView.findViewById(R.id.HoroscopeRecyclerView);
            Context context = getContext();
            recyclerView.setAdapter(new HoroscopeRVAdapter(horoscopeList, context ));
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getArguments()!= null) {
            String idS = getArguments().getString("id");
            int idB =Integer.parseInt(idS);
            NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
            Bundle bundle = new Bundle();
            bundle.putInt("id", idB);
            navController.navigate(R.id.action_horoscopeList_to_horoscopeInfoFragment, bundle);
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mViewModel = null;
    }
}