package com.manish.obaa.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.manish.obaa.fragment.BookingFragment;
import com.manish.obaa.fragment.HelpFragment;
import com.manish.obaa.fragment.HomeFragment;
import com.manish.obaa.fragment.Members;
import com.manish.obaa.fragment.ProfileFragment;

public class FragmentAdapter extends FragmentPagerAdapter {
    public FragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i)
        {
            case 0: return new HomeFragment();
            case 1: return new HelpFragment();
            case 2: return new BookingFragment();
            case 3: return new ProfileFragment();
            case 4: return new Members();

        }
        return null;
    }

    @Override
    public int getCount() {
        return 5;
    }
}
