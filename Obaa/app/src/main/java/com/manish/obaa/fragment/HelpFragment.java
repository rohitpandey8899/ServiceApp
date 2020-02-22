package com.manish.obaa.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.manish.obaa.R;
import com.manish.obaa.utils.Api;
import com.manish.obaa.utils.Preference;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class HelpFragment extends Fragment implements View.OnClickListener {

    private EditText messageHelpET, nameHelpET, emailHelpET, mobileHelpET;
    private Button sendFeedbackHelpBT;

    public HelpFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_help, container, false);

        initialisation(view);

        setListener();

        return view;

    }

    private void setListener() {
        sendFeedbackHelpBT.setOnClickListener(this);
    }

    private void initialisation(View view) {

        Preference preference = new Preference(Objects.requireNonNull(getContext()));

        sendFeedbackHelpBT = view.findViewById(R.id.sendFeedbackHelpBT);

        messageHelpET = view.findViewById(R.id.messageHelpET);
        nameHelpET = view.findViewById(R.id.nameHelpET);
        nameHelpET.setText(preference.getName());

        emailHelpET = view.findViewById(R.id.emailHelpET);
        emailHelpET.setText(preference.getEmail());

        mobileHelpET = view.findViewById(R.id.mobileHelpET);
        mobileHelpET.setText(preference.getContactNo());

    }

    @Override
    public void onClick(View view) {

        if (view == sendFeedbackHelpBT) {
            sendFeedback();
        }
    }

    private void sendFeedback() {

        final String message = messageHelpET.getText().toString();
        final String name = nameHelpET.getText().toString();
        final String email = emailHelpET.getText().toString();
        final String mobile = mobileHelpET.getText().toString();

        if (message.length() == 0) {
            showAlert("Please write message");
        } else if (name.length() == 0) {
            showAlert("Please enter Name");
        } else if (email.length() == 0) {
            showAlert("Please enter Email");
        } else if (mobile.length() == 0) {
            showAlert("Please enter Mobile No");
        } else if (mobile.length() != 10) {
            showAlert("Please enter Mobile No atleast 10 digit");
        } else {

            StringRequest sendFeedback = new StringRequest(Request.Method.POST, Api.help, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.e("HELP_RESPONSE", response);
                    if (response.equals("TRUE")) {
                        Toast.makeText(getActivity(), "Query Submitted.", Toast.LENGTH_SHORT).show();

                    } else {

                        Toast.makeText(getActivity(), "Query not Submitted.", Toast.LENGTH_SHORT).show();

                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("EXEPTION_HELP", error.getMessage());
                }
            }) {
                @Override
                protected Map<String, String> getParams() {
                    HashMap<String, String> map = new HashMap<>();
                    map.put("message", message);
                    map.put("name", name);
                    map.put("email", email);
                    map.put("mobile", mobile);
                    return map;
                }
            };
            Volley.newRequestQueue(Objects.requireNonNull(getActivity())).add(sendFeedback);

        }
    }

    private void showAlert(String message) {

        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();

    }


}
