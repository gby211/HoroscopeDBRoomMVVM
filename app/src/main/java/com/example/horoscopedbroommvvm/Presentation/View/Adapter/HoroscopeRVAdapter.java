package com.example.horoscopedbroommvvm.Presentation.View.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.horoscopedbroommvvm.Presentation.Model.HoroscopeDTO;
import com.example.horoscopedbroommvvm.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class HoroscopeRVAdapter extends RecyclerView.Adapter<HoroscopeRVAdapter.HoroscopeViewHolder> {

    private List<HoroscopeDTO> horoscopeDTOS = new ArrayList<>();
    private Context context;

    public HoroscopeRVAdapter(List<HoroscopeDTO> horoscopeDTOS,Context context) {
        this.horoscopeDTOS = horoscopeDTOS;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public HoroscopeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item,parent,false);
        return new HoroscopeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HoroscopeViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Bundle bundle = new Bundle();
        bundle.putInt("id",horoscopeDTOS.get(position).getId());

        holder.cardView.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_horoscopeList_to_horoscopeInfoFragment, bundle));

        HoroscopeDTO DTO = horoscopeDTOS.get(position);
        holder.textViewDate.setText(DTO.getDate());
        holder.textViewZodiac.setText(DTO.getZodiac());
        holder.textViewInfo.setText(DTO.getInfo());
    }

    @Override
    public int getItemCount() {
        return horoscopeDTOS.size();
    }


    public List<HoroscopeDTO> getData() {
        return horoscopeDTOS;
    }

    public class HoroscopeViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewZodiac;
        private TextView textViewDate;
        private TextView textViewInfo;
        private CardView cardView;

        public HoroscopeViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardRV);
            textViewZodiac = itemView.findViewById(R.id.textViewZodiac);
            textViewDate = itemView.findViewById(R.id.textViewDate);
            textViewInfo = itemView.findViewById(R.id.textViewInfo);
        }
    }
    public String getFullinfoStr(int id){
        HoroscopeDTO DTO = horoscopeDTOS.get(id);
        return DTO.getFullInfo();
    }
}
