package com.example.horoscopedbroommvvm.Presentation.View;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.horoscopedbroommvvm.Presentation.ViewModel.HoroscopeViewModel;
import com.example.horoscopedbroommvvm.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class UpdateFragment extends Fragment {

    HoroscopeViewModel mViewModel;
    private View mView;
    FloatingActionButton fab;
    ImageButton bck;
    EditText editTextDate,editTextZodiac, editTextInfo;
    int id;

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

        bck = mView.findViewById(R.id.back_button1);
        fab = mView.findViewById(R.id.fab1);
        editTextDate = mView.findViewById(R.id.editTextTextPersonName11);
        editTextZodiac = mView.findViewById(R.id.editTextTextPersonName21);
        editTextInfo = mView.findViewById(R.id.editTextTextPersonName31);


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
}