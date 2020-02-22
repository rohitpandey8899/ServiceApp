package com.manish.obaa.startup;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.manish.obaa.MainActivity;
import com.manish.obaa.R;
import com.manish.obaa.utils.Preference;
import com.manish.obaa.utils.SQLiteHelper;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        SQLiteDatabase database = openOrCreateDatabase(SQLiteHelper.DATABASE_NAME, MODE_PRIVATE, null);
        SQLiteHelper helper = new SQLiteHelper(Splash.this);
        helper.onCreate(database);

        Preference preference = new Preference(this);

        if (preference.isLogin()) {

            startActivity(new Intent(this, MainActivity.class));
            finish();

        } else {
            startActivity(new Intent(this, Login.class));
            finish();

        }

    }
}