package com.manish.obaa.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.manish.obaa.MainActivity;
import com.manish.obaa.R;

public class BookingFragment extends Fragment {

    private RelativeLayout bokingRL;
    private ProgressBar progressBar;
    public BookingFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_booking, container, false);

        bokingRL = view.findViewById(R.id.bokingRL);
        progressBar = view.findViewById(R.id.progressBar);

        view.findViewById(R.id.bookServiceBT).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.getViewPager().setCurrentItem(0);
            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.GONE);
                bokingRL.setVisibility(View.VISIBLE);
            }
        }, 2000);


        return view;
    }

}
