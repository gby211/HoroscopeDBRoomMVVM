package com.example.horoscopedbroommvvm.Presentation.View;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.provider.ContactsContract;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.horoscopedbroommvvm.R;

import java.util.ArrayList;

public class PhoneCallFragment extends Fragment {

    ArrayList<String> nameList, phoneList, allList;
    ListView listV;
    String bundleInfo;
    private static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 666;
    private static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION1 = 777;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        nameList = new ArrayList<>();
        phoneList = new ArrayList<>();
        allList = new ArrayList<>();

        bundleInfo = getArguments().getString("info");
    }

    @SuppressLint("Range")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mView = inflater.inflate(R.layout.fragment_phone_call, container, false);

        listV = mView.findViewById(R.id.listView1);


        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.READ_CONTACTS},
                    MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        } else if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.SEND_SMS},
                    MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION1);
        } else if (ActivityCompat.checkSelfPermission(getContext(),
                Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {

            ContentResolver cr = getActivity().getBaseContext().getContentResolver();
            Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
                    null, null, null, null);

            if ((cur != null ? cur.getCount() : 0) > 0) {
                while (cur != null && cur.moveToNext()) {
                    @SuppressLint("Range") String id = cur.getString(
                            cur.getColumnIndex(ContactsContract.Contacts._ID));
                    @SuppressLint("Range") String name = cur.getString(cur.getColumnIndex(
                            ContactsContract.Contacts.DISPLAY_NAME));

                    if (cur.getInt(cur.getColumnIndex(
                            ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
                        Cursor pCur = cr.query(
                                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                                null,
                                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                                new String[]{id}, null);
                        while (pCur.moveToNext()) {
                            @SuppressLint("Range") String phoneNo =
                                    pCur.getString(pCur.getColumnIndex(
                                            ContactsContract.CommonDataKinds.Phone.NUMBER));
                            Log.i("TAG", "Name: " + name);
                            nameList.add(name);
                            Log.i("TAG", "Phone Number: " + phoneNo);
                            phoneList.add(phoneNo);
                        }
                        pCur.close();
                    }
                }
            }
            if (cur != null) {
                cur.close();
            }
            String alldata = "";
            for (int i = 0; i < nameList.size(); i++) {
                alldata = nameList.get(i) + ": " + phoneList.get(i);
                allList.add(alldata);
            }
            Log.d("TAG", "getContactList: name " + nameList.toString());
            Log.d("TAG", "getContactList: phone " + phoneList.toString());

            Log.d("TAG", "getContactList: name " + nameList.size());
            Log.d("TAG", "getContactList: phone " + phoneList.size());


            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                    android.R.layout.simple_list_item_1, allList);
            listV.setAdapter(adapter);
            listV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {

                    DialogFragmentPhoneCall dialog = new DialogFragmentPhoneCall();
                    Bundle args = new Bundle();
                    args.putString("number",phoneList.get(position));
                    args.putString("person",nameList.get(position));
                    args.putString("info",bundleInfo);
                    dialog.setArguments(args);
                    dialog.show(getChildFragmentManager(),"phoneCallDialogChose");
//                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
//
//                    builder.setTitle("Отправить sms?");
//                    builder.setMessage("Отправит sms на номер" + phoneList.get(position));
//                    builder.setPositiveButton("Да", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int id) {
//                            SmsManager smsManager = SmsManager.getDefault();
//                            smsManager.sendTextMessage(phoneList.get(position), null, bundleInfo,
//                                    null, null);
//                            Log.d("TAG", "onItemClick: " + "========================");
//                            Toast.makeText(getActivity(), "SMS отправлено",
//                                    Toast.LENGTH_LONG).show();
//                        }
//                    });
//                    builder.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int id) {
//                        }
//                    });
//                    builder.setCancelable(true);
//                    builder.create();
                }
            });
        }
        return mView;
    }

}