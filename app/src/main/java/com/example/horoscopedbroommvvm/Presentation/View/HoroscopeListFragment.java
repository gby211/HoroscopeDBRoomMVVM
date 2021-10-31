package com.example.horoscopedbroommvvm.Presentation.View;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
    int role;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_horoscope_list, container, false);

        role = getArguments().getInt("role",0);
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
                if(role == 1) {
                    mViewModel.delete(((HoroscopeRVAdapter) recyclerView1.getAdapter()).getData().get(position));
                }else {
                    createAlertDialog1();
                    recyclerView1.getAdapter().notifyDataSetChanged();
                }
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
                bundle.putInt("id",
                        ((HoroscopeRVAdapter) recyclerView1.getAdapter()).getData().get(position).getId());
                bundle.putString("zod",
                        ((HoroscopeRVAdapter) recyclerView1.getAdapter()).getData().get(position).getZodiac());
                bundle.putString("inf",
                        ((HoroscopeRVAdapter) recyclerView1.getAdapter()).getData().get(position).getInfo());
                bundle.putString("date",
                        ((HoroscopeRVAdapter) recyclerView1.getAdapter()).getData().get(position).getDate());
                bundle.putString("ful",
                        ((HoroscopeRVAdapter) recyclerView1.getAdapter())
                                .getFullinfoStr(position));
                Log.d("tag", "onSwiped: "+ ((HoroscopeRVAdapter) recyclerView1.getAdapter())
                        .getFullinfoStr(position));
                Log.d("tag", "onSwiped: "+ bundle.toString());
                if(role == 1) {
                    navController.navigate(R.id.action_horoscopeList_to_updateFragment, bundle);
                }else{
                    createAlertDialog1();
                    recyclerView1.getAdapter().notifyDataSetChanged();
                }

            }
        }).attachToRecyclerView(recyclerView1);

        return mView;
    }




    private void createAlertDialog1() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Вы не одминистратор");
        builder.setMessage("Купите подписку что бы изменять будующие ... )");
        builder.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                    }
                });
        builder.setNegativeButton("Купить",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        Toast.makeText(getContext(), "Пока нельзя изменять будующие, даже за деньги (", Toast.LENGTH_SHORT).show();
                    }
                });
        builder.show();

    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(HoroscopeViewModel.class);

        mViewModel.getAllData().observe(getViewLifecycleOwner(),
                (List<HoroscopeDTO> horoscopeList) -> {
                    RecyclerView recyclerView = mView.findViewById(R.id.HoroscopeRecyclerView);
                    Context context = getContext();
                    recyclerView.setAdapter(new HoroscopeRVAdapter(horoscopeList, context));
                });
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getArguments() != null && !getArguments().isEmpty()) {
            String idS = getArguments().getString("id");
            if (idS == null){
                return;
            }
            int idB = Integer.parseInt(idS);
            NavController navController = Navigation.findNavController(getActivity(),
                    R.id.nav_host_fragment);
            Bundle bundle = new Bundle();
            bundle.putInt("id", idB);
            getArguments().clear();
            navController.navigate(R.id.action_horoscopeList_to_horoscopeInfoFragment, bundle);
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mViewModel = null;
    }
}