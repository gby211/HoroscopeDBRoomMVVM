package com.example.horoscopedbroommvvm.Presentation.View;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.horoscopedbroommvvm.Presentation.ViewModel.HoroscopeViewModel;
import com.example.horoscopedbroommvvm.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class UpdateFragment extends Fragment {

    HoroscopeViewModel mViewModel;
    private View mView;
    FloatingActionButton fab;
    ImageButton bck;
    private LocalDateTime time;
    EditText editTextDate,editTextZodiac, editTextInfo;
    int id;
    String inf, date, zod;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(HoroscopeViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_update, container, false);

        id = getArguments().getInt("id");
        zod = getArguments().getString("zod");
        inf = getArguments().getString("inf");
        date = getArguments().getString("date");




        bck = mView.findViewById(R.id.back_button1);
        fab = mView.findViewById(R.id.fab1);
        editTextDate = mView.findViewById(R.id.editTextTextPersonName11);

        editTextDate.setText(date);
        editTextDate.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();

                DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, month);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        time = LocalDateTime.ofInstant(calendar.toInstant(), calendar.getTimeZone().toZoneId());
                        editTextDate.setText(time.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
                    }
                };

                new DatePickerDialog(getActivity(), dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });

        editTextZodiac = mView.findViewById(R.id.editTextTextPersonName21);
        editTextZodiac.setText(zod);
        editTextInfo = mView.findViewById(R.id.editTextTextPersonName31);
        editTextInfo.setText(inf);


        bck.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).popBackStack();
            }
        });


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editTextDate.getText().toString().isEmpty() & !editTextZodiac.getText().toString().isEmpty() & !editTextInfo.getText().toString().isEmpty()) {
                    mViewModel.update1(
                            id,
                            editTextDate.getText().toString(),
                            editTextZodiac.getText().toString(),
                            editTextInfo.getText().toString()
                    );
                    Navigation.findNavController(v).popBackStack();
                } else {
                    Toast.makeText(getContext(), "Вы ввели не все данные", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return mView;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        mViewModel = null;
    }
}