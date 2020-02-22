package com.manish.obaa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.manish.obaa.adapter.FragmentAdapter;
import com.manish.obaa.ahbottomnavigation.AHBottomNavigation;
import com.manish.obaa.ahbottomnavigation.AHBottomNavigationItem;
import com.manish.obaa.fragment.Members;
import com.manish.obaa.utils.Preference;
import com.manish.obaa.utils.ViewpagerWithoutSwipe;

@SuppressWarnings("deprecation")
public class MainActivity extends AppCompatActivity {

    private static ViewpagerWithoutSwipe viewPagerMain;
    private TextView cityTVMain;
    private Preference preference;

    public static ViewPager getViewPager()
    {
        return viewPagerMain;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        viewPagerMain = findViewById(R.id.viewPagerMain);
        cityTVMain = findViewById(R.id.cityTVMain);
        preference = new Preference(this);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager());
        viewPagerMain.setAdapter(fragmentAdapter);

        cityTVMain.setText(preference.getCity());

        cityTVMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, GetCityActivity.class);
                startActivityForResult(myIntent, 100);
            }
        });

        final AHBottomNavigation bottomNavigation = findViewById(R.id.bottomNavViewMain);
        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.home, R.drawable.ic_home_black_24dp, R.color.black);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.query, R.drawable.ic_message_black_24dp, R.color.black);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.booking, R.drawable.notepad1, R.color.black);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem(R.string.profile, R.drawable.ic_person_outline_black_24dp, R.color.black);
        AHBottomNavigationItem item5 = new AHBottomNavigationItem("Members", R.drawable.ic_person_outline_black_24dp, R.color.black);

        // Add items
        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);
        bottomNavigation.addItem(item3);
        bottomNavigation.addItem(item4);
        bottomNavigation.addItem(item5);

        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);
        bottomNavigation.setAccentColor(getResources().getColor(R.color.colorPrimary));

        // Set listeners
        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                viewPagerMain.setCurrentItem(position);

                Log.e("VIEW_PAGER_POS", String.valueOf(position));

//                if (bottomNavigation.getCurrentItem() == 0)
//                {
//                    viewPagerMain.setCurrentItem(0);
//
//                }else
//                if (bottomNavigation.getCurrentItem() == 1)
//                {
//                    viewPagerMain.setCurrentItem(1);
//                }else
//                if (bottomNavigation.getCurrentItem() == 2)
//                {
//                    viewPagerMain.setCurrentItem(2);
//                }else
//                if (bottomNavigation.getCurrentItem() == 3)
//                {
//                    viewPagerMain.setCurrentItem(3);
//                }

                return true;
            }
        });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100) {
            if (resultCode == Activity.RESULT_OK && data != null) {

                String city = data.getStringExtra("data");
                cityTVMain.setText(city);
                preference.setCity(city);

            }
        }
    }

    @Override
    protected void onResume() {
        cityTVMain.setText(preference.getCity());
        super.onResume();
    }
}