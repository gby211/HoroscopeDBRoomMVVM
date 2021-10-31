package com.example.horoscopedbroommvvm.Presentation.View;

import android.content.Intent;
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
import android.widget.Toast;

import com.example.horoscopedbroommvvm.Presentation.Model.HoroscopeDTO;
import com.example.horoscopedbroommvvm.Presentation.Model.ProfileDTO;
import com.example.horoscopedbroommvvm.Presentation.ViewModel.HoroscopeViewModel;
import com.example.horoscopedbroommvvm.R;
import com.google.android.gms.common.SignInButton;


public class LoginFragment extends Fragment {

    Button btn_enter, btn_register;
    private View mView;
    HoroscopeViewModel mViewModel;
    EditText edEmail, edPassword;
    SignInButton signInButton;

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
        mView = inflater.inflate(R.layout.fragment_log_in, container, false);


        btn_enter = mView.findViewById(R.id.button_enter);
        btn_register = mView.findViewById(R.id.button_register);
        edEmail = mView.findViewById(R.id.editTextEmail);
        edPassword = mView.findViewById(R.id.editTextPassword);
        signInButton = mView.findViewById(R.id.sign_in_button);





        btn_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!edEmail.getText().toString().isEmpty() & !edPassword.getText().toString().isEmpty()) {
                    mViewModel.getProfile(edEmail.getText().toString(),
                            edPassword.getText().toString()).observe(getViewLifecycleOwner(),
                            (ProfileDTO profileDTO) -> {
                                if (profileDTO != null) {
                                    Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_horoscopeList);
                                }else {
                                    Toast.makeText(getContext(), "Пароль или логин неправилен",
                                            Toast.LENGTH_SHORT).show();
                                }
                            });
                } else {
                    Toast.makeText(getContext(), "Вы ввели не все данные", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //btn_enter.setOnClickListener(Navigation.createNavigateOnClickListener(R.id
        // .action_loginFragment_to_horoscopeList));


        btn_register.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_loginFragment_to_regFragment));


        return mView;
    }
}