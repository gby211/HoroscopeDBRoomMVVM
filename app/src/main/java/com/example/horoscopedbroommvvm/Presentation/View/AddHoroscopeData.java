package com.example.horoscopedbroommvvm.Presentation.View;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.horoscopedbroommvvm.Presentation.ViewModel.HoroscopeViewModel;
import com.example.horoscopedbroommvvm.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddHoroscopeData extends Fragment {

    HoroscopeViewModel mViewModel;
    private View mView;
    FloatingActionButton fab;
    ImageButton bck;
    EditText editTextDate,editTextZodiac, editTextInfo;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_add_horoscope_data, container, false);

        bck = mView.findViewById(R.id.back_button);
        fab = mView.findViewById(R.id.fab);
        editTextDate = mView.findViewById(R.id.textViewDate);
        editTextZodiac = mView.findViewById(R.id.textViewZodiac);
        editTextInfo = mView.findViewById(R.id.textViewInfo);

        bck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).popBackStack();
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editTextDate.getText().toString().isEmpty() || !editTextZodiac.getText().toString().isEmpty() || !editTextInfo.getText().toString().isEmpty()) {
                    mViewModel.insert(
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
}