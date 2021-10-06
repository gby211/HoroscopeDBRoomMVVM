package com.example.horoscopedbroommvvm.Presentation.View;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class DialogFragmentPhoneCall extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        String phoneNumber,namePerson,bundleInfo;
        phoneNumber = getArguments().getString("number");
        namePerson = getArguments().getString("person");
        bundleInfo = getArguments().getString("info");

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Отправить sms?");
        builder.setMessage("Отправит sms контакту "+ namePerson +"\nНа номер: " + phoneNumber);
        builder.setPositiveButton("Да", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(phoneNumber, null, bundleInfo,
                        null, null);
                Log.d("TAG", "onItemClick: " + "========================");
                Toast.makeText(getActivity(), "SMS отправлено",
                        Toast.LENGTH_LONG).show();
            }
        });
        builder.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            }
        });
        builder.setCancelable(true);
        return builder.create();
    }
}
