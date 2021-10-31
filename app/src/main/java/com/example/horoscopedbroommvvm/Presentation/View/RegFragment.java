package com.example.horoscopedbroommvvm.Presentation.View;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.horoscopedbroommvvm.Presentation.ViewModel.HoroscopeViewModel;
import com.example.horoscopedbroommvvm.R;


public class RegFragment extends Fragment {

    private View mView;
    Button btn_reg;
    ImageButton bck;
    EditText edEmail,edPassword;
    HoroscopeViewModel mViewModel;

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
        mView = inflater.inflate(R.layout.fragment_reg, container, false);


        edEmail = mView.findViewById(R.id.editTextEmail);
        edPassword = mView.findViewById(R.id.editTextPassword);
        bck = mView.findViewById(R.id.back_button);
        bck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).popBackStack();
            }
        });

        btn_reg = mView.findViewById(R.id.button_register);
        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!edEmail.getText().toString().isEmpty() & !edPassword.getText().toString().isEmpty()){
                    mViewModel.insertProfile1(
                            edEmail.getText().toString(),
                            edPassword.getText().toString()
                    );
                    Toast.makeText(getContext(), "Вы успешно зарегистрировались", Toast.LENGTH_SHORT).show();
                    Navigation.findNavController(view).popBackStack();

                }else {
                    Toast.makeText(getContext(), "Вы ввели не все данные", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return mView;
    }
}