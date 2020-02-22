package com.manish.obaa.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.manish.obaa.R;
import com.manish.obaa.startup.Login;
import com.manish.obaa.utils.Preference;

import java.util.Objects;

public class ProfileFragment extends Fragment {

    public ProfileFragment() { }

    private TextView emailProfileTV;
    private TextView contactProfileTV;
    private TextView genderProfileTV;
    private Preference preference;
    private Button logOutBT;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        init(view);

        emailProfileTV.setText(preference.getEmail());
        contactProfileTV.setText(preference.getContactNo());
        genderProfileTV.setText(preference.getGender());

        logOutBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preference.setLogin(false);
                preference.clearAll();
                startActivity(new Intent(getContext(), Login.class));
                Objects.requireNonNull(getActivity()).finish();
            }
        });


        return view;
    }

    private void init(View view) {
        emailProfileTV = view.findViewById(R.id.emailProfileTV);
        contactProfileTV = view.findViewById(R.id.contactProfileTV);
        genderProfileTV = view.findViewById(R.id.genderProfileTV);
        logOutBT = view.findViewById(R.id.logoutBT);
        preference = new Preference(Objects.requireNonNull(getContext()));
    }
}