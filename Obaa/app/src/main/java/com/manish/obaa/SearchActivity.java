package com.manish.obaa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SearchActivity extends AppCompatActivity {

    private EditText searchET;
    private Button clearBT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchET = findViewById(R.id.searchET2);
        clearBT = findViewById(R.id.clearBT2);

        setLister();

    }

    private void setLister() {

        searchET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.length() != 0)
                {
                    clearBT.setVisibility(View.VISIBLE);
                }else {
                    clearBT.setVisibility(View.GONE);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        clearBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                searchET.getText().clear();

            }
        });
    }

}
