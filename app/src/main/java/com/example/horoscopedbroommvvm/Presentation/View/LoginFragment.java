package com.example.horoscopedbroommvvm.Presentation.View;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.util.Log;
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
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;


public class LoginFragment extends Fragment {

    Button btn_enter, btn_register;
    private View mView;
    HoroscopeViewModel mViewModel;
    EditText edEmail, edPassword;
    SignInButton signInButton;
    GoogleSignInClient mGoogleSignInClient;
    int RC_SIGN_IN = 100;

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



        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(getActivity(), gso);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        });



        btn_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!edEmail.getText().toString().isEmpty() & !edPassword.getText().toString().isEmpty()) {
                    mViewModel.getProfile(edEmail.getText().toString(),
                            edPassword.getText().toString()).observe(getViewLifecycleOwner(),
                            (ProfileDTO profileDTO) -> {
                                if (profileDTO != null) {
                                    Bundle bundle = new Bundle();
                                    bundle.putInt("role",profileDTO.getRole());
                                    Log.d("ggsss", String.valueOf(profileDTO.getRole()));
                                    Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_horoscopeList,bundle);
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            Navigation.findNavController(mView).navigate(R.id.action_loginFragment_to_horoscopeList);

        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("handleSignInResult", "signInResult:failed code=" + e.getStatusCode());
            Toast.makeText(getContext(), "Регистрация не прошла", Toast.LENGTH_SHORT).show();
        }
    }

}